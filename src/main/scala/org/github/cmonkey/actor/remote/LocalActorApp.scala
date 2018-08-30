package org.github.cmonkey.actor.remote

import akka.actor.{ActorSystem, Props}

object LocalActorApp extends App{

  val actorSystem = ActorSystem("local-system")

  val localActor = actorSystem.actorOf(Props[LocalActor], "localActor")

  localActor ! Init
  localActor ! SendNoReturn

}
