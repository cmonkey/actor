package org.github.cmonkey.actor.simple

import akka.actor.Actor

class Stanley extends Actor{
  override def receive: Receive = {
    case msg: String => {
      println(s"sender path name = ${sender().path.name}")
      println(msg)
      val name = sender().path.name
      sender() ! s"Hello ${name}"
    }

    case _ =>
  }
}
