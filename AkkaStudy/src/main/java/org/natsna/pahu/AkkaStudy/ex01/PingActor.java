package org.natsna.pahu.AkkaStudy.ex01;


import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class PingActor extends UntypedActor {

	private LoggingAdapter log = Logging.getLogger(getContext().system(), this);
	private ActorRef pongA;
	private ActorRef pongB;
	private ActorRef pongC;
	private ActorRef pongD;
	private int count;
	private int pcount;
	

	/**
	 * onReceive 가 수행되기 전에 실행, 즉 객체가 만들어지자마자 실행되는 메소드. 개인적인 생각으로는 생성자가 만들어지고 호출이
	 * 되는 메소드로 생각이 든다.
	 */
	@Override
	public void preStart() throws Exception {

		this.pongA = context().actorOf(Props.create(PongActor.class, getSelf(), "pongA"), "pongActor");
		this.pongB = context().actorOf(Props.create(PongActor.class, getSelf(), "pongB"), "pongBctor");
		this.pongC = context().actorOf(Props.create(PongActor.class, getSelf(), "pongC"), "pongCctor");
		this.pongD = context().actorOf(Props.create(PongActor.class, getSelf(), "pongD"), "pongDctor");
		this.count = 0;
		this.pcount = 0;

	}

	@Override
	public void onReceive(Object arg0) throws Exception {
		if (arg0 instanceof String) {
			String msg = (String) arg0;
			log.info("Ping received {}", msg);
			count++;
			log.info("count: " + count);
			pongA.tell("'pingA'", getSelf());
			pongB.tell("'pingB'", getSelf());
			pongC.tell("'pingC'", getSelf());
			pongD.tell("'pingD'", getSelf());

			pcount++;
			log.info("pcount: " + pcount);
		}

	}

}
