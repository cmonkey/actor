package org.github.cmonkey.actor.backPressure

import akka.actor.ActorRef

case class Job(id:Long , input: Int, replyTo: ActorRef)
