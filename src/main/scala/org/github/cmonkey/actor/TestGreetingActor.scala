package org.github.cmonkey.actor

import akka.actor.{ActorSystem, Props}

object TestGreetingActor {

  def main(args: Array[String]): Unit = {

    val actorSystem = ActorSystem("greetingActorSystem")

    val greeter = actorSystem.actorOf(Props[GreetingActor], name = "greeter")

    greeter ! Greeting("who")

    println("msg")
  }

}
