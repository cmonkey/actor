package org.github.cmonkey.actor.remote

import akka.actor.Actor
import akka.util.Timeout
import com.typesafe.config.ConfigFactory
import akka.pattern.ask

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._

class LocalActor extends Actor{
  val path = ConfigFactory.defaultApplication().getString("remote.actor.name")
  implicit val timout = Timeout(5.seconds)
  implicit val executorContent = ExecutionContext.global

  val remoteActor = context.actorSelection(path)

  override def receive: Receive = {
    case Init => "Init local actor"
    case SendNoReturn =>
      for {
        r <- remoteActor.ask("hello remote actor")
      }yield  println(r)
  }
}
