package org.github.cmonkey.actor

import akka.actor.{Actor, Props}

class MagicActor(magicNumber: Int)  extends Actor{
  override def receive: Receive = {

    case x: Int => sender() ! (x + magicNumber)
  }
}

object MagicActor{
  def props(magicNumber: Int): Props = Props(new MagicActor(magicNumber))
}
