package org.natsna.pahu.AkkaStudy.ex02;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class GoodOrBadMain {

	/**
	 * Akka, Let It Crash
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ActorSystem actorSystem = ActorSystem.create("TestSystem");
		ActorRef ping = actorSystem.actorOf(Props.create(PingActor.class), "pingActor");
		// ping.tell("good", ActorRef.noSender());
		ping.tell("bad", ActorRef.noSender());
	}

}
