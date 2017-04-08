package org.natsna.pahu.AkkaStudy.ex02;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import scala.Option;

public class Ping2Actor extends UntypedActor {

	private LoggingAdapter log = Logging.getLogger(getContext().system(), this);

	public Ping2Actor() {
		log.info("Ping2Actor constructgor..");
	}
	
	
	
	@Override
	public void preRestart(Throwable reason, Option<Object> message) throws Exception {
		log.info("Ping2Actor preRestart..");
		
	}
	
	
	@Override
	public void postStop() throws Exception {
		log.info("Ping2Actor postStop..");
	}
	
	@Override
	public void postRestart(Throwable reason) throws Exception {
		log.info("Ping2Actor postRestart..");
		
	}
	
	@Override
	public void onReceive(Object arg0) throws Exception {
		if (arg0 instanceof String) {
			String msg = (String) arg0;
			if (msg.equals("good")) {
				goodWork();
			}else if(msg.equals("bad")){
				badWork();
			}

		}

	}

	private void goodWork() throws Exception {
		log.info("Ping2Actor is good.");
	}

	/**
	 * 일부러 ArithmeticException을 발생시킴
	 * 여기서 Arithmetic 이란 산수를 뜻 함.
	 * @throws Exception
	 */
	private void badWork() throws Exception {
		int a = 1 / 0;
	}

}
