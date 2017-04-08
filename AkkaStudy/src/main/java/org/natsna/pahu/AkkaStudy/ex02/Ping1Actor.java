package org.natsna.pahu.AkkaStudy.ex02;

import akka.actor.ActorRef;
import akka.actor.OneForOneStrategy;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.actor.SupervisorStrategy;
import akka.actor.SupervisorStrategy.Directive;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Function;
import scala.concurrent.duration.Duration;
/**
 * SupervisorStrategy 를 임포트를 수동으로 했다. ide 문제가 있엇음 
 */
import static akka.actor.SupervisorStrategy.*;

/**
 * 자식 액터를 감시하기 위한 중간자 
 * @author glqdl
 *
 */
public class Ping1Actor extends UntypedActor {

	private LoggingAdapter log = Logging.getLogger(context().system(), this);
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

			if (msg.equals("good") || msg.equals("bad")) {
				log.info("Ping1Actor received {}", msg);
				child1.tell(msg, getSender());
				child2.tell(msg, getSender());

			}

		}else{
			unhandled(arg0);
		}

	}

	/**
	 * SupervisorStrategy = 직역하면 ' 감시자 전략 '
	 */
	private static SupervisorStrategy strategy = 
			new OneForOneStrategy(10, Duration.create("1 minute"),
			new Function<Throwable, Directive>() {
				@Override
				public Directive apply(Throwable t) throws Exception {
					if (t instanceof ArithmeticException) {
					    // Ping2Actor는 "bad" 메시지를 받으면 ArithmeticException을 발생 
						return resume();
					} else if (t instanceof NullPointerException) {
						// Ping3Actor는 "bad" 메시지를 받으면 NullPointerException을 발생
						return restart();
					} else if (t instanceof IllegalArgumentException) {
						return stop();
					} else {
						return escalate();
					}
				}

			});	
	
	@Override
	public SupervisorStrategy supervisorStrategy() {
		return strategy;
	}
}
