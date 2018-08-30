package org.github.cmonkey.actor.company

import akka.actor.{Actor, Props}
import akka.event.Logging

class ManagerActor extends Actor{
  val log = Logging(context.system, this)

  def receive: Receive = {
    case m: Meeting => 
      sender() ! Confirm("I have receive command", self.path)
    case d: DoAction => 
      val workerActor = context.actorOf(Props[WorkerActor], "worker")
      workerActor forward d
  }
}
