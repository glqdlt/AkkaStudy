package org.natsna.pahu.AkkaStudy.ex05;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class CalculationActor extends UntypedActor {
	private LoggingAdapter log = Logging.getLogger(getContext().system(), this);

	@Override
	public void onReceive(Object arg0) throws Exception {
		if (arg0 instanceof Integer) {
			Integer msg = (Integer) arg0;

			log.info("CalculationActor received {}", msg);
			work(msg);
			getSender().tell(msg * 2, getSelf());
		}
	}

	private void work(Integer n) throws Exception {
		log.info("CalculationActor working on" + n);
		Thread.sleep(1000);
		log.info("CalculationActor completed" + n);

	}

}
