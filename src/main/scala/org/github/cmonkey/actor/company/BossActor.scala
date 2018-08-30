package org.github.cmonkey.actor.company

import akka.actor.{Actor, Props}
import akka.event.Logging
import akka.util.Timeout
import scala.concurrent.duration._
import akka.pattern.{ask, pipe}
class BossActor extends Actor{
  val log = Logging(context.system, this)

  implicit val askTimeout = Timeout(5 seconds)

  import context.dispatcher 
  var taskCount = 0

  def receive: Receive = {
    case b: Business =>
      log.info("I must to do some thing, go, go, go!")
      println(self.path.address)

      val managerActors = (1 to 3).map(f => 
          context.actorOf(Props[ManagerActor], s"manager${f}"))

      managerActors foreach{
        _ ? Meeting("Meeting to discuss big plans") map {
          case c: Confirm => 
            log.info(c.actorPath.parent.toString)

            val manager = context.actorSelection(c.actorPath)

            manager ! DoAction("Do thing")
        }
      }

      case d: Done => {
        taskCount += 1

        if(taskCount == 3){
          log.info("the project is done, we will earn much money")
          context.system.terminate()
        }
      }
  }

}
