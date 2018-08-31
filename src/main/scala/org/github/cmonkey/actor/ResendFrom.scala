package org.github.cmonkey.actor

import akka.actor.ActorRef

final case class ResendFrom(msgId: Int, resendTo:ActorRef)
