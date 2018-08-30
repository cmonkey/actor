package org.github.cmonkey.actor.robot

import akka.actor.{ActorSystem, Props}

object RobotActorApp extends App{

  val actorSystem = ActorSystem("robot-system")

  val robotActor = actorSystem.actorOf(Props(new RobotActor{}), "robot-actor")

  robotActor ! TurnOnLight(1)

  robotActor ! BoilWater(2)

  robotActor ! "who are you"

  actorSystem.terminate()

}
