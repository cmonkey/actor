package org.github.cmonkey.actor.company

import akka.actor.{ActorSystem, Props}

object BossActorApp extends App{

  val actorSystem = ActorSystem("company-system")

  val bossActor = actorSystem.actorOf(Props[BossActor], "boss")

  bossActor ! Business("Fitness industry has great prospects")

  actorSystem.terminate()

}
