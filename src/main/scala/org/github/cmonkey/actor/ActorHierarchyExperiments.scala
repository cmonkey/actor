package org.github.cmonkey.actor

import akka.actor.{ActorSystem, Props}

import scala.io.StdIn

object ActorHierarchyExperiments extends App{

  val system = ActorSystem("testSystem")

  val firstRef = system.actorOf(Props[PrintMyActorRefActor], "first-actor")

  println(s"firstRef ${firstRef}")

  firstRef ! "printit"

  println(">>> Press ENTER to exit <<<")

  try {
    StdIn.readLine()
  }finally {
    system.terminate()
  }


}
