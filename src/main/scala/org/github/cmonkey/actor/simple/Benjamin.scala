package org.github.cmonkey.actor.simple

import akka.actor.{Actor, ActorRef}

class Benjamin extends Actor{
  override def receive: Receive = {
    case friend: ActorRef => {
      val name = friend.path.name
      println(s"sender path name = ${name}")
      friend ! s"Hello ${name} !"
    }

    case msg: String => {
      println(msg)
      context.system.terminate()
    }

    case _ =>
  }
}
