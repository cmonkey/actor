package org.github.cmonkey.actor.supervisor

import akka.actor.SupervisorStrategy._
import akka.actor.{Actor, OneForOneStrategy, Props}

import scala.concurrent.duration._

class Supervisor extends Actor{

  override val supervisorStrategy = 
    OneForOneStrategy(maxNrOfRetries = 10, withinTimeRange = 1 minute){
      case _: ArithmeticException => Resume
      case _: NullPointerException => Restart
      case _: IllegalArgumentException => Stop
      case _: Exception => Escalate
    }
  var childIndex = 0

  override def receive: Receive = {
    case p: Props => 
      childIndex += 1

      sender() ! context.actorOf(p, s"child${childIndex}")
  }
}
