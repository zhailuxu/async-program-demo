package example.akka.remote.server;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import example.akka.remote.shared.LoggingActor;

import static example.akka.remote.shared.Messages.*;

public class CalculatorActor extends UntypedActor {

    private LoggingAdapter log = Logging.getLogger(getContext().system(), this);


    @Override
    public void onReceive(Object message) throws Exception {
        log.info("onReceive({})", message);

        if (message instanceof Sum) {
            log.info("got a Sum message");
            Sum sum = (Sum) message;

            int result = sum.getFirst() + sum.getSecond();
            getSender().tell(new Result(result), getSelf());

        } else {
            unhandled(message);
        }
    }
}
