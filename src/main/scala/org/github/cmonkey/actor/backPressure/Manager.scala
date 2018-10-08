package org.github.cmonkey.actor.backPressure

import java.math.{MathContext, RoundingMode}

import akka.actor.{Actor, Props}

class Manager(startTime: Long) extends Actor{

  private val workerStream: Iterator[Job] = Iterator.range(1, 1000000).map(x => Job(x, x, self))

  private val mc = new MathContext(10000, RoundingMode.HALF_EVEN)

  private var approximation = BigDecimal(0, mc)

  private var outstandingWork = 0

  (1 to 8).foreach(_ => context.actorOf(Props(new Worker(self))))

  override def receive: Receive = {

    case WorkRequest(worker, items) => {
      workerStream.take(items).foreach(job => {
        worker ! job
        outstandingWork += 1
      })
    }

    case JobResult(id, result) => {

      approximation = approximation + result

      outstandingWork -= 1

      if(outstandingWork == 0 && workerStream.isEmpty){
        println(s"final result: $approximation, time spent is ${System.currentTimeMillis() - startTime}ms")
        context.system.terminate()
      }
    }
  }
}
