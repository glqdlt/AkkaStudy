package org.natsna.pahu.AkkaStudy.ex06;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class AgentMain {
	public static void main(String[] args) {
		ActorSystem actorSystem = ActorSystem.create("TestSystem");
		ActorRef agentActor = actorSystem.actorOf(Props.create(AgentActor.class),"agentActor");
		agentActor.tell("Start", ActorRef.noSender());
	}
}
