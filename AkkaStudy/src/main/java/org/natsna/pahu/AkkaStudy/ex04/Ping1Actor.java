package org.natsna.pahu.AkkaStudy.ex04;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class Ping1Actor extends UntypedActor {

	private LoggingAdapter log = Logging.getLogger(getContext().system(), this);

	@Override
	public void onReceive(Object arg0) throws Exception {
		if (arg0 instanceof Integer) {
			Integer msg = (Integer) arg0;
			log.info("Ping1Actor({}) received {}", hashCode(), msg);
			work(msg);

		}

	}

	private void work(Integer n) throws Exception {
		log.info("Ping1Actor({}) working on {}", hashCode(), n);
		log.info("Ping1Actor({}) completed", hashCode());
	}

}
