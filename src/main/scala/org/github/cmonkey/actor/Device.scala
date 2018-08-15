package org.github.cmonkey.actor

import akka.actor.{Actor, ActorLogging, Props}

object Device{
  def props(groupId: String, deviceId: String): Props = Props(new Device(groupId, deviceId))

  final case object ReadTemperature(requestId: Long)

  final case class RespondTemperature(requestId: Long, value: Option[Double])

}
