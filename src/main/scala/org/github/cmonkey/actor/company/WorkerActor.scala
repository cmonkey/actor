package org.github.cmonkey.actor.company

import akka.actor.Actor
import akka.event.Logging

class WorkerActor extends Actor{
  val log = Logging(context.system, this)

  def receive: Receive = {
    case d: DoAction => 
      log.info("I have receive task")
      sender() ! Done("I hava done work")
  }
}
