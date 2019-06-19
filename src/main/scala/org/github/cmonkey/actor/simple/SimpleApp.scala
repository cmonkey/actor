package org.github.cmonkey.actor.simple

import akka.actor.{ActorSystem, Props}

object SimpleApp extends App{

  val system = ActorSystem("Greeting")

  val benjamin = system.actorOf(Props[Benjamin], "benjamin")

  val stanley = system.actorOf(Props[Stanley], "stanley")

  benjamin ! stanley

}
