package org.github.cmonkey.actor.drink

import akka.actor.{ActorSystem, Props}

object DrinkActorApp {

  def main(args: Array[String]): Unit = {
    val system = ActorSystem("drinkActorSystem")
    val props = Props[DrinkActor]

    val drinkActor = system.actorOf(props, "drink-actor-1")

    drinkActor ! "tea"
    drinkActor ! "coffer"
    drinkActor ! "watter"

    system.terminate()
  }

}
