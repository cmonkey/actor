package org.github.cmonkey.actor.robot

import akka.actor.Actor
import akka.event.Logging

class RobotActor extends Actor{
  val logging = Logging(context.system, this)

  override def receive: Receive = {

    case t: TurnOnLight => logging.info(s"${t.message} after ${t.time} hour")

    case b: BoilWater => logging.info(s"${b.message} after ${b.time} hour")

    case _ => logging.info("I can not handle the message")
  }
}
