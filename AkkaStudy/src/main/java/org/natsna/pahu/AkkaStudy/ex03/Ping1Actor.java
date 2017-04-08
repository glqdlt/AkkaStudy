package org.natsna.pahu.AkkaStudy.ex03;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class Ping1Actor extends UntypedActor {

	private LoggingAdapter log = Logging.getLogger(getContext().system(), this);
	private ActorRef child1;
	private ActorRef child2;

	public Ping1Actor() {
		child1 = context().actorOf(Props.create(Ping2Actor.class), "ping2Actor");
		child2 = context().actorOf(Props.create(Ping3Actor.class), "ping3Actor");
	}

	@Override
	public void onReceive(Object arg0) throws Exception {
		if (arg0 instanceof String) {
			String msg = (String) arg0;
			log.info("Ping 1 received {}", msg);
			child1.tell(msg, getSender());
			child2.tell(msg, getSender());

		}
	}

}
