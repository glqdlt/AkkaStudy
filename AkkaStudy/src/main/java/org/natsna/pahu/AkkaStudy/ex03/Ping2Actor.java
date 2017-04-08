package org.natsna.pahu.AkkaStudy.ex03;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class Ping2Actor extends UntypedActor {

	private LoggingAdapter log = Logging.getLogger(getContext().system(), this);

	@Override
	public void onReceive(Object arg0) throws Exception {
		if (arg0 instanceof String) {

			String msg = (String) arg0;
			log.info("Ping2 received {}", msg);
			work();
			getSender().tell("done", getSelf());

		}

	}

	private void work() throws Exception {
		Thread.sleep(1000);
		log.info("Ping2 working..");
	}
}
