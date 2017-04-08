package org.natsna.pahu.AkkaStudy.ex01;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class PingActor extends UntypedActor {

	private LoggingAdapter log = Logging.getLogger(getContext().system(), this);
	private ActorRef pong;

	/**
	 * onReceive 가 수행되기 전에 실행, 즉 객체가 만들어지자마자 실행되는 메소드. 개인적인 생각으로는 생성자 안에서 호출이
	 * 일어나는 메소드로 생각이 든다.
	 */
	@Override
	public void preStart() throws Exception {

		this.pong = context().actorOf(Props.create(PongActor.class, getSelf()), "pongActor");

	}

	@Override
	public void onReceive(Object arg0) throws Exception {
		if(arg0 instanceof String){
			String msg = (String) arg0;
			
			log.info("Ping received {}", msg);
			pong.tell("'ping'", getSelf());
			
		}

	}

}
