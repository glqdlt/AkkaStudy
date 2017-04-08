package org.natsna.pahu.AkkaStudy.ex03;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActorWithStash;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Procedure;

public class PingActor extends UntypedActorWithStash {

	private LoggingAdapter log = Logging.getLogger(getContext().system(), this);
	private ActorRef child;

	public PingActor() {
		child = context().actorOf(Props.create(Ping1Actor.class), "ping1Actor");
		getContext().become(initial);
	}

	@Override
	public void onReceive(Object arg0) throws Exception {

	}

	/**
	 * 맨 처음 상태에서 "work"를 받으면 zeroDone 상태가 된다.
	 */
	Procedure<Object> initial = new Procedure<Object>() {

		@Override
		public void apply(Object arg0) throws Exception {

			if (arg0.equals("work")) {
				log.info("Received the work !!!");
				child.tell("work", getSelf());
				getContext().become(zeroDone);

			} else {
				stash();
			}

		}

	};

	/**
	 * zeroDone 상태에서 "done" 메시지를 받으면 oneDone 상태가 된다. Procedure == 숨기다 라는 뜻
	 */
	Procedure<Object> zeroDone = new Procedure<Object>() {

		@Override
		public void apply(Object arg0) throws Exception {
			if (arg0.equals("done")) {
				log.info("Received the first done");
				getContext().become(oneDone);

			} else {

				/**
				 * stash == 숨기다 라는 뜻
				 */
				stash();
			}
		}
	};

	Procedure<Object> oneDone = new Procedure<Object>() {

		@Override
		public void apply(Object arg0) throws Exception {
			if (arg0.equals("done")) {
				log.info("Received the second done");
				unstashAll();
				getContext().become(allDone);
			} else {
				stash();
			}
		}
	};

	Procedure<Object> allDone = new Procedure<Object>() {

		@Override
		public void apply(Object arg0) throws Exception {
			if(arg0.equals("reset")){
				log.info("received a reset");
				getContext().become(initial);
			}
		}
	};

}
