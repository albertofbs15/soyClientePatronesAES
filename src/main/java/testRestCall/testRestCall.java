package testRestCall;

import akka.actor.ActorSystem;
import domain.Convenio.IntermediateRoutingServiceProvider;
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
        CompletionStage<IntermediateRoutingServiceProvider> x = httpClient.doGet(uri, IntermediateRoutingServiceProvider.class);

        x.toCompletableFuture().get();

    }

}
