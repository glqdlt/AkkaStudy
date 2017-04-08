package org.natsna.pahu.AkkaStudy.ex03;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class SystemMain {

	/**
	 * Akka, Let It Crash
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ActorSystem actorSystem = ActorSystem.create("TestSystem");
		ActorRef ping = actorSystem.actorOf(Props.create(PingActor.class), "pingActor");
		ping.tell("work", ActorRef.noSender());
		ping.tell("reset", ActorRef.noSender());
		ping.tell("work", ActorRef.noSender());
		ping.tell("work", ActorRef.noSender());
		ping.tell("work", ActorRef.noSender());
		ping.tell("work", ActorRef.noSender());
		ping.tell("work", ActorRef.noSender());
		ping.tell("work", ActorRef.noSender());
		ping.tell("work", ActorRef.noSender());
		ping.tell("work", ActorRef.noSender());
		ping.tell("work", ActorRef.noSender());
		ping.tell("work", ActorRef.noSender());
		ping.tell("work", ActorRef.noSender());
		ping.tell("work", ActorRef.noSender());
		ping.tell("work", ActorRef.noSender());
		ping.tell("work", ActorRef.noSender());
		ping.tell("work", ActorRef.noSender());
		ping.tell("work", ActorRef.noSender());
		ping.tell("work", ActorRef.noSender());
		ping.tell("work", ActorRef.noSender());
		ping.tell("work", ActorRef.noSender());
		ping.tell("work", ActorRef.noSender());
		ping.tell("reset", ActorRef.noSender());
	}

}
