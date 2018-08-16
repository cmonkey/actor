package org.github.cmonkey.actor

import akka.actor.{Actor, ActorRef, Props}
import akka.event.Logging

class DeveloperActor(tester: ActorRef) extends Actor{
  import DeveloperActor._

  val log = Logging.getLogger(context.system, this)

  def receive = {
    case feature: NewFeature => {
      log.info(s"Working on feature ${feature.name}")
      tester ! feature
    }

    case Bug => {
      log.info("fixing a bug")

      tester ! Fix
    }
    case _ => log.info("playing starcraft2")
  }
}

object DeveloperActor{
  def props(tester: ActorRef) = Props(new DeveloperActor(tester))

  case class NewFeature(name: String)
  object Bug
  object Fix
}
