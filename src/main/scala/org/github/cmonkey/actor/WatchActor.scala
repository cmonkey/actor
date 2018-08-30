package org.github.cmonkey.actor

import akka.actor.{Actor, ActorSystem, Props, Terminated}

class WatchActor extends Actor{
  val child = context.actorOf(Props.empty, "child")
  context.watch(child)

  val actorSystem = ActorSystem.create("system")

  var lastSender = actorSystem.deadLetters

  override def receive: Receive = {

    case "kill" => context.stop(child); lastSender = sender()
    case Terminated(`child`) => lastSender ! "finished"
  }
}
