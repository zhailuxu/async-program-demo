package example.akka.remote.server;

import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;

public class Server {

	public static void main(String... args) {
		// 1. 创建Actor系统
		ActorSystem system = ActorSystem.create("AkkaRemoteServer", ConfigFactory.load());

		// 2. 创建Actor
		system.actorOf(Props.create(CalculatorActor.class), "CalculatorActor");
	}
}
