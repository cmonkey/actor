package org.github.cmonkey.actor

import akka.actor.{ActorSystem, Props}

object WatchActorApp extends App{

  val actorSystem = ActorSystem.create("watchActorSystem")

  val watchActor = actorSystem.actorOf(Props[WatchActor], "watchActor")

  watchActor ! "kill"

  actorSystem.terminate()

}
