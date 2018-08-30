package org.github.cmonkey.actor.cluster

import java.util.concurrent.atomic.AtomicInteger

import akka.actor.{ActorSystem, Props}

import scala.concurrent.duration._

object ClusterClientApp extends App{
  TransformationFromtendApp.main(Seq("2551").toArray)
  TransformationBackendApp.main(Seq("8001").toArray)
  TransformationBackendApp.main(Seq("8002").toArray)
  TransformationBackendApp.main(Seq("8003").toArray)

  val system = ActorSystem("clusterclientsystem")

  val clientJobTransformationSendingActor = 
    system.actorOf(Props[ClientJobTransformationSendingActor], "clientJobTransformationSendingActor")

  val counter = new AtomicInteger 
  import system.dispatcher

  system.scheduler.schedule(2.seconds, 2.seconds){
    clientJobTransformationSendingActor ! Send(counter.incrementAndGet)
  }

}
