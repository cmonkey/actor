package org.github.cmonkey.actor.drink

import akka.actor.Actor
import akka.event.Logging

class DrinkActor extends Actor{

  val log = Logging(context.system, this)

  def receive ={
    case "tea" => log.info("tea time")
    case "coffer" => log.info("Coffer time")
    case _ => log.info("Hmmm...")

  }

}