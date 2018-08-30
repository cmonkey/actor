package org.github.cmonkey.actor.cluster

import akka.actor.{ActorSystem, Props}
import com.typesafe.config.ConfigFactory

object TransformationBackendApp {
  def main(args: Array[String]) = {
    val port = if (args.isEmpty) "2553" else args(0)
    val config = ConfigFactory.parseString(s"akka.remote.netty.tcp.port=$port").
      withFallback(ConfigFactory.load())

    val system = ActorSystem("ClusterSystem", config)
    system.actorOf(Props[TransformationBackendActor], name = "backend")
  }

}
