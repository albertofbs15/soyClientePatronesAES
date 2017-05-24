package core;

import akka.actor.Extension;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
import scala.concurrent.duration.FiniteDuration;

import java.util.concurrent.TimeUnit;

/**
 * Interface to define a domain service.
 */
public interface DomainService extends Extension {
  /**
   * Duration of a request.
   */
  FiniteDuration DEFAULT_DURATION = Duration.create(5, TimeUnit.SECONDS);

  default <T> T result(Future<T> future) {
    return result(future, DEFAULT_DURATION);
  }

  /**
   * Method to get a result from a service.
   * 
   * @param future Future to get the info from.
   * @param duration The duration of the timeout.
   * @param <T> The type of the result.
   * @return The result.
   */
  default <T> T result(Future<T> future, Duration duration) {
    try {
      return Await.result(future, duration);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }

}
