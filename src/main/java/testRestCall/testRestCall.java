package testRestCall;

import akka.actor.ActorSystem;
import akka.http.javadsl.Http;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.stream.ActorMaterializer;
import akka.stream.Materializer;
import core.HttpServiceClient;
import domain.calculadora.Calculadora;

import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

/**
 * Created by alber on 5/23/2017.
 */
public class testRestCall {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final ActorSystem system = ActorSystem.create();

         final HttpServiceClient httpClient;
        httpClient = new HttpServiceClient(system, 5000);

        String uri = "http://akka.io";
        CompletionStage<Calculadora> x = httpClient.doGet(uri, Calculadora.class);

        x.toCompletableFuture().get();

    }

}
