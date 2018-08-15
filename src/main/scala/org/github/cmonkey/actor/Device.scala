package org.github.cmonkey.actor

final case object ReadTemperature(requestId: Long)

final cas class RespondTemperature(requestId: Long, value: Option[Double])

import akka.actor.{Actor, ActorLogging, Props}

object Device{
  def props(groupId: String, deviceId: String): Props = Props(new Device(groupId, deviceId))
}
