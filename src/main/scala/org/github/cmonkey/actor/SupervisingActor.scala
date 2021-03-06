package org.github.cmonkey.actor

import akka.actor.{Actor, Props}

class SupervisingActor extends Actor {
  val child = context.actorOf(Props[SupervisedActor], "supervised-actor")

  override def receive: Receive = {
    case "FailChild" => child ! "fail"
  }
}
