package org.github.cmonkey.actor.supervisor

import akka.actor.{ActorSystem, Props}

object SupervisorActorApp extends App{

  val actorSystem = ActorSystem("supervisor-system")

  val supervisor = actorSystem.actorOf(Props[Supervisor], "supervisor")

  supervisor ! Props[Child]

  actorSystem.terminate()

}
