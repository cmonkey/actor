package org.github.cmonkey.actor

import akka.actor.ActorSystem

object MagicActorApp extends App{

  val actorSystem = ActorSystem.create("magicActorSystem")

  val magicActor = actorSystem.actorOf(MagicActor.props(42), "magic")

  magicActor ! 50

  actorSystem.terminate()

}
