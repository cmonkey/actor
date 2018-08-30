package org.github.cmonkey.actor.remote

import akka.actor.Actor

class RemoteActor extends Actor{
  override def receive: Receive = {
    case msg: String =>
      println(s"RemoteActor receive msg = ${msg}")

      sender() ! "Hello from the RemoteActor"
  }
}
