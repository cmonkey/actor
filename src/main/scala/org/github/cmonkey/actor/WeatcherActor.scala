package org.github.cmonkey.actor

import akka.actor.Actor

class WeatcherActor extends Actor{
  override def receive: Receive = {
    case msg => println(msg)
  }
}
