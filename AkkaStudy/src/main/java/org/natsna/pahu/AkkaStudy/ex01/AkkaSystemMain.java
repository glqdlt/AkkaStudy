package org.natsna.pahu.AkkaStudy.ex01;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class AkkaSystemMain {

	public static void main(String[] args) {

		ActorSystem actorSystem = ActorSystem.create("TestSystem");
		ActorRef ping = actorSystem.actorOf(Props.create(PingActor.class), "pingActor");
		ping.tell("'start'", ActorRef.noSender());

	}

}
