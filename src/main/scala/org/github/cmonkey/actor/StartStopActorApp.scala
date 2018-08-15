package org.github.cmonkey.actor

import akka.actor.{ActorSystem, Props}

object StartStopActorApp extends App{

  val system = ActorSystem("startStopSystem")

  val first = system.actorOf(Props[StartStopActor1], "first")

  first ! "stop"

  system.terminate()

}
