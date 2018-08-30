package org.github.cmonkey.actor.cluster

import akka.actor.{Actor, ActorRef, Terminated}
import akka.util.Timeout

import scala.concurrent.duration._
import akka.pattern.ask
import akka.pattern.pipe

import scala.concurrent.ExecutionContext

class TransformationFrontendActor extends Actor {

  implicit def executeContent = ExecutionContext.global
  var backends = IndexedSeq.empty[ActorRef]

  var jobCounter = 0

  def receive = {
    case job: TransformationJob if backends.isEmpty =>
      sender() ! JobFailed("Service unavailable, try again later", job)
    case job: TransformationJob => 
      jobCounter += 1
      implicit val timeout = Timeout(5 seconds)

      val backend = backends(jobCounter % backends.size) 
      println(s"the backend is ${backends} and the job is ${job}")
      val result = (backend ? job)
        .map(x => x.asInstanceOf[TransformationResult])
        result pipeTo sender

    case BackendRegistration if (backends.contains(sender())) =>
      context.watch(sender())
      backends = backends :+ sender()

    case Terminated(a) => 
      backends = backends.filterNot(_ == a)


  }
}
