package org.github.cmonkey.actor

import akka.actor.{Actor, ActorLogging}

class GreetingActor extends Actor with ActorLogging{
  override def receive: Receive = {

    case Greeting(who) => log.info(s"message who = ${who}")
  }
}
