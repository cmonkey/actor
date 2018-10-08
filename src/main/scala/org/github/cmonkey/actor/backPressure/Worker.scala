package org.github.cmonkey.actor.backPressure

import java.math.{MathContext, RoundingMode}

import akka.actor.{Actor, ActorRef}

class Worker(manager: ActorRef) extends Actor{

  private val mc = new MathContext(100, RoundingMode.HALF_EVEN)
  private val plus = BigDecimal(1, mc)
  private val minus  = BigDecimal(-1, mc)

  private var requested = 0

  def request(): Unit = {
    if(requested < 5){
      manager ! WorkRequest(self, 10)
      requested += 10
    }
  }

  request()

  override def receive: Receive = {
    case Job(id, data, replyTo) => {
      requested -= 1
      request()

      val sign = if((data & 1) == 1) plus else minus
      val result = sign / data 

      replyTo ! JobResult(id, result)
    }
  }
}
