package org.github.cmonkey.actor.cluster

import akka.actor.{ActorSystem, Props}
import akka.cluster.client.ClusterClientReceptionist
import com.typesafe.config.ConfigFactory

object TransformationFromtendApp {
  def main(args: Array[String]) = {
    val port = if (args.isEmpty) "0" else args(0)
    val config = ConfigFactory.parseString(s"akka.remote.netty.tcp.port=$port").
      withFallback(ConfigFactory.load())

    val system = ActorSystem("ClusterSystem", config)
    val frontend = system.actorOf(Props[TransformationFrontendActor], name = "frontend")
    ClusterClientReceptionist(system).registerService(frontend)
  }

}
