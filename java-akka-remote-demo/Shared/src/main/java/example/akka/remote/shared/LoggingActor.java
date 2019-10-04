package example.akka.remote.shared;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

import java.io.Serializable;

/**
 * This actor logs to the console.
 * Intented to run on a remote actorsystem for testing.
 */
public class LoggingActor extends UntypedActor implements Serializable {
    private LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof String) {
            log.info("Got message: " + message);
        }
    }
}
