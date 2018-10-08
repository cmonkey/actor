package org.github.cmonkey.actor.backPressure

import akka.actor.{Actor, ActorRef}

case class WorkRequest(worker: ActorRef, items: Int)
