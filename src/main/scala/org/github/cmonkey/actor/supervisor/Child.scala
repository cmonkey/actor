package org.github.cmonkey.actor.supervisor

import akka.actor.Actor
import akka.event.Logging

class Child extends Actor{
  val log = Logging(context.system, this)

  var state = 0

  override def receive: Receive = {

    case ex: Exception => throw ex

    case x: Int => state = x

    case s: Command if s.content == "get" =>
      log.info(s"the ${s.self} state is ${state}")

      sender() ! state
  }
}
