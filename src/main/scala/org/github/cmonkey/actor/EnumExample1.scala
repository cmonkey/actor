package org.github.cmonkey.actor

object EnumExample1 {

  sealed abstract class Status

  case object Pending extends Status

  case object InProgress extends Status

  case object Finished extends Status
}
