package org.github.cmonkey.actor

import akka.actor.Actor

class StartStopActor2 extends Actor{
  override def preStart(): Unit = println("second start")

  override def postStop(): Unit = println("second first")

  override def receive: Receive = {
    Actor.emptyBehavior
  }
}
