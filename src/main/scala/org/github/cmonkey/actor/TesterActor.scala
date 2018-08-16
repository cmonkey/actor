package org.github.cmonkey.actor

import akka.actor.{Actor, Props}
import akka.event.Logging
import org.github.cmonkey.actor.DeveloperActor.{Bug, Fix, NewFeature}

import scala.util.Random

class TesterActor extends Actor{

  val log = Logging.getLogger(context.system, this)

  def receive = {

    case feature: NewFeature => {
      log.info(s"Testing ${feature.name}")

      val bugExists = Random.nextInt(10)

      if(bugExists > 7){
        log.info("A bug was found")

        sender() ! Bug
      }
    }

    case Fix => log.info("Verifying the fix...")
    case _ => log.info("Watching YouTuble")
  }
}

object TesterActor{
  def props = Props(new TesterActor)
}
