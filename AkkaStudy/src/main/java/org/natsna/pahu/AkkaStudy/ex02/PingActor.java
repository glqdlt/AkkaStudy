package org.natsna.pahu.AkkaStudy.ex02;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class PingActor  extends UntypedActor{
	
	private LoggingAdapter log = Logging.getLogger(context().system(), this);
	private ActorRef child;
	
	public PingActor() {
		child = context().actorOf(Props.create(Ping1Actor.class),"ping1Actor");
		
	}

	@Override
	public void onReceive(Object arg0) throws Exception {
		
		if(arg0 instanceof String){
			String msg = (String)arg0;
			if(msg.equals("good") || msg.equals("bad")){
				child.tell(msg, getSelf());
			}
			else if(msg.equals("done")){
				log.info("all works are success. completed");
			}
			
		}else{
			unhandled(arg0);
		}
		
	}

}
