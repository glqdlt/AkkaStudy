package org.natsna.pahu.AkkaStudy.ex01;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class PongActor extends UntypedActor {

	private LoggingAdapter log = Logging.getLogger(getContext().system(), this);
	private ActorRef ping;
	private String pingName;

	public PongActor(ActorRef ping, String pingName) {
		this.ping = ping;
		this.pingName = pingName;

	}

	@Override
	public void onReceive(Object arg0) throws Exception {
		if (arg0 instanceof String) {
			String msg = (String) arg0;
			log.info(pingName+"Pong received {}", msg);
			ping.tell(pingName+"'pong'", getSelf());
			Thread.sleep(1000);

		}

	}

}
