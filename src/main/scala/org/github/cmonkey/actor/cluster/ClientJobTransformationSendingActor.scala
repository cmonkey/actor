package org.github.cmonkey.actor.cluster

import akka.actor.{Actor, ActorPath}
import akka.cluster.client.{ClusterClient, ClusterClientSettings}
import akka.pattern.Patterns
import akka.util.Timeout

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._
import scala.util.{Failure, Success}

class ClientJobTransformationSendingActor extends Actor{
  val initialContacts = Set(
    ActorPath.fromString("akka.tcp://ClusterSystem@127.0.0.1:2551/system/receptionist"))

    val settings = ClusterClientSettings(context.system)
    .withInitialContacts(initialContacts)

    val c = context.system.actorOf(ClusterClient.props(settings), "cluster-client")

    implicit val executionContext = ExecutionContext.global

    def receive = {
      case TransformationResult(result) => {
        println(s"client response and the result is ${result}")
      }

      case Send(counter) => {
        val job = TransformationJob("hello-" + counter)

        implicit val timeout = Timeout(5 seconds)

        val result = Patterns.ask(c, ClusterClient.Send("/user/frontend", job, localAffinity = true), timeout)

        result.onComplete{
          case Success(transformationResult) => {
            self ! transformationResult
          }

          case Failure(t) => println("An error has occured: " + t.getMessage)
        }
      }
    }
}


