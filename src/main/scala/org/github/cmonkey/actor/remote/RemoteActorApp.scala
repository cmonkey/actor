package org.github.cmonkey.actor.remote

import akka.actor.{ActorSystem, Props}

object RemoteActorApp extends App{

  val actorSystem = ActorSystem("remoteSystem")

  val remoteActor = actorSystem.actorOf(Props[RemoteActor], "remoteActor")

  remoteActor ! "msg"

  //actorSystem.terminate()
}
