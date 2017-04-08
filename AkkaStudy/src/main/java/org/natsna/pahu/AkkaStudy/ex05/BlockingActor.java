package org.natsna.pahu.AkkaStudy.ex05;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.pattern.Patterns;
import akka.util.Timeout;
import scala.concurrent.Await;
import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

public class BlockingActor extends UntypedActor {

	private LoggingAdapter log = Logging.getLogger(getContext().system(), this);
	private ActorRef child;
	private Timeout timeout = new Timeout(Duration.create(5, "seconds"));
	private final ExecutionContext ec;

	public BlockingActor() {
		child = context().actorOf(Props.create(CalculationActor.class), "calculationActor");
		ec = context().system().dispatcher();
	}

	@Override
	public void onReceive(Object arg0) throws Exception {
		if (arg0 instanceof Integer) {
			Future<Object> future = Patterns.ask(child, arg0, timeout);
			Integer result = (Integer) Await.result(future, timeout.duration()); // 블록킹
			log.info("Final result is " + (result + 1));

		} else if (arg0 instanceof String) {
			log.info("BlockingActor received a message: " + arg0);

		}

	}

}
