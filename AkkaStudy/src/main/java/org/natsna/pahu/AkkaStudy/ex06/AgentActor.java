package org.natsna.pahu.AkkaStudy.ex06;

import akka.actor.UntypedActor;
import akka.agent.Agent;
import akka.dispatch.ExecutionContexts;
import akka.dispatch.Mapper;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import scala.concurrent.ExecutionContext;

public class AgentActor extends UntypedActor {

	private LoggingAdapter log = Logging.getLogger(context().system(), this);

	@Override
	public void onReceive(Object arg0) throws Exception {

		ExecutionContext ec = ExecutionContexts.global();
		Agent<Integer> agent = Agent.create(5, ec);
		agent.send(new Mapper<Integer, Integer>() {
			public Integer apply(Integer i) {
				return i * 2;

			}
		});
		log.info("Current agent value = " + agent.get());

		Thread.sleep(100);
		log.info("Current agent value = " + agent.get());
	}

}
