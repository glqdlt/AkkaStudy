package org.natsna.pahu.AkkaStudy.ex04;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.routing.RoundRobinPool;

public class PingActor extends UntypedActor{

	private LoggingAdapter log = Logging.getLogger(context().system(), this);
	private ActorRef childRouter;
	
	
	public PingActor() {
		childRouter = getContext().actorOf(new RoundRobinPool(50).props(Props.create(Ping1Actor.class)),"ping1Actor");
	}
	
	@Override
	public void onReceive(Object arg0) throws Exception {
		if(arg0 instanceof String){
			for (int i = 0; i < 100; i++) {
				childRouter.tell(i, getSelf());
			}
			log.info("PingActor send 10 messages to the router");
		}else{
			unhandled(arg0);
		}
		
	}

}
