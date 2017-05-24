package core;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;

import com.fasterxml.jackson.databind.ObjectMapper;

import akka.NotUsed;
import akka.actor.ActorSystem;
import akka.dispatch.Futures;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.http.javadsl.Http;
import akka.http.javadsl.model.HttpHeader;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.model.StatusCodes;
import akka.japi.Pair;
import akka.stream.ActorMaterializer;
import akka.stream.OverflowStrategy;
import akka.stream.QueueOfferResult;
import akka.stream.javadsl.Flow;
import akka.stream.javadsl.Keep;
import akka.stream.javadsl.Sink;
import akka.stream.javadsl.Source;
import akka.stream.javadsl.SourceQueue;
import akka.util.ByteString;
import scala.compat.java8.FutureConverters;
import scala.concurrent.Promise;
import scala.util.Try;
import util.JacksonJdk8;

public class HttpServiceClient {

  private static final String CHARSET = "UTF-8";
  private static final ObjectMapper MAPPER = JacksonJdk8.mapper();

  protected final ActorSystem system;
  private final ActorMaterializer materializer;
  private final SourceQueue<Pair<HttpRequest, Promise<HttpResponse>>> queue;
  private final int bufferSize;
  private final LoggingAdapter log;

  /**
   * Creates a http service client.
   *
   * @param system actor system
   * @param bufferSize buffer size
   */
  public HttpServiceClient(ActorSystem system, int bufferSize) {
    this.system = system;
    this.bufferSize = bufferSize;
    this.materializer = ActorMaterializer.create(system);
    this.queue = queue(flow());
    this.log = Logging.getLogger(system, this);
  }

  private Flow<Pair<HttpRequest, Promise<HttpResponse>>, Pair<Try<HttpResponse>, Promise<HttpResponse>>, NotUsed> flow() {
    return Http.get(system).superPool(materializer);
  }

  final SourceQueue<Pair<HttpRequest, Promise<HttpResponse>>> queue(Flow flow) {
    return (SourceQueue<Pair<HttpRequest, Promise<HttpResponse>>>) Source.<Pair<HttpRequest, Promise<HttpResponse>>>queue(bufferSize,
        OverflowStrategy.dropNew())
        .via(flow)
        .toMat(Sink.foreach((Pair<Try<HttpResponse>, Promise<HttpResponse>> p) -> p.second()
            .complete(p.first())), Keep.left())
        .run(materializer);
  }

  public <T> CompletionStage<T> doGet(String uri, Class<T> clazz, HttpHeader... headers) {
    CompletionStage<HttpResponse> request = request(HttpRequest.create(uri));
    return request.thenComposeAsync(transformResponse(uri, clazz));
  }

  public CompletionStage<HttpResponse> request(HttpRequest request) {
    log.debug("Making request {}", request);
    Promise<HttpResponse> promise = Futures.promise();
    return queue.offer(Pair.create(request, promise))
        .thenCompose(buffered -> {
          if (buffered instanceof QueueOfferResult.Enqueued$) {
            return FutureConverters.toJava(promise.future())
                .thenApply(resp -> {
                  if (log.isDebugEnabled()) {
                    log.debug("Got response {} {}", resp.status(), resp.getHeaders());
                  }
                  return resp;
                });
          } else {
            log.error("Could not buffer request {}", request);
            return CompletableFuture.completedFuture(HttpResponse.create()
                .withStatus(StatusCodes.SERVICE_UNAVAILABLE));
          }
        });
  }

  private CompletionStage<String> payload(HttpResponse response, ActorMaterializer materializer) {
    return response.entity().toStrict(1000, materializer).thenApplyAsync(strict -> {
      final ByteString data = strict.getData();
      return data.utf8String();
    });
  }

  private <T> Function<HttpResponse, CompletionStage<T>> transformResponse(String uri, Class<T> clazz) {
    return (response) -> {
      CompletionStage<String> payload = payload(response, materializer);
      if (response.status().isFailure()) {
        return payload.thenCompose(responsePayload -> {
          CompletableFuture<T> exception = new CompletableFuture<>();
          if (response.status() == StatusCodes.NOT_FOUND) {
            exception.completeExceptionally(
                new IllegalArgumentException(MessageFormat.format("{0} - Entity not found: {1} {2}", uri, response.status(), responsePayload)));
          } else {
            exception.completeExceptionally(new IllegalArgumentException(MessageFormat.format("{0} - {1} - {2}", uri, response.status(), responsePayload)));
          }
          return exception;
        });
      }
      return payload.thenApply(httpResponsePayload -> unmarshall(httpResponsePayload, clazz));
    };
  }

  private <T> T unmarshall(String payload, Class<T> clazz) {
    try {
      if (clazz == String.class) {
        return (T) payload;
      }
      return MAPPER.readValue(payload, clazz);
    } catch (IOException ioe) {
      throw new IllegalArgumentException(ioe);
    }
  }

}
