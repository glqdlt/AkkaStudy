package org.natsna.pahu.AkkaStudy.ex05;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.pattern.Patterns;
import akka.util.Timeout;
import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
import akka.dispatch.*;

public class NonblockingAction extends UntypedActor {

	private LoggingAdapter log = Logging.getLogger(getContext().system(), this);
	private ActorRef child;
	private Timeout timeout = new Timeout(Duration.create(5, "seconds"));
	private final ExecutionContext ec;

	public NonblockingAction() {
		child = context().actorOf(Props.create(CalculationActor.class), "calculationActor");
		ec = context().system().dispatcher();
	}

	@Override
	public void onReceive(Object arg0) throws Exception {
		
		if(arg0 instanceof Integer){
			Future<Object> future = Patterns.ask(child, arg0, timeout);
			
			// onSuccess, onComplete, onFailure 등은 blocking 동작이 아니다.
			
			future.onSuccess(new SaySuccess<Object>(), ec);
			future.onComplete(new SayComplete<Object>(), ec);
			future.onFailure(new SayFailure<Object>(), ec);
			
			
		}else if(arg0 instanceof String){
			log.info("NonblockingActor received a meesage: "+arg0);
			
		}

		
	}
	
	public final class SaySuccess<T> extends OnSuccess<T>{
		@Override
		public void onSuccess(T arg0) throws Throwable {
			log.info("Succeeded with "+ arg0);
		}
	}

	public final class SayFailure<T> extends OnFailure{

		@Override
		public void onFailure(Throwable arg0) throws Throwable {
			log.info("Failed with"+ arg0);
		}
		
	}
	
	public final class SayComplete<T> extends OnComplete<T>{

		@Override
		public void onComplete(Throwable arg0, T arg1) throws Throwable {
			log.info("Completed");
		}
		
	}
}