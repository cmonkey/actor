package org.github.cmonkey.actor

import akka.actor.{Actor, Props}
import akka.event.Logging

class PrintMyActorRefActor extends Actor{
  val log = Logging(context.system, this)
  override def receive: Receive = {

    case "printit" => {
      val secondRef = context.actorOf(Props.empty, "second-actor")
      log.info(s"seconds ${secondRef}")
    }
  }
}
