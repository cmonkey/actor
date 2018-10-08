package org.github.cmonkey.actor.backPressure

import akka.actor.{ActorSystem, Props}
import scala.concurrent.duration._

import scala.concurrent.Await

object ManagerActorApp extends App{

  val actorSystem = ActorSystem("manager")

  actorSystem.actorOf(Props(new Manager(System.currentTimeMillis())))

  Await.result(actorSystem.whenTerminated, 1.minute)

}
