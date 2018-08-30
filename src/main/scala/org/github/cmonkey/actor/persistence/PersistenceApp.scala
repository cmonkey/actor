package org.github.cmonkey.actor.persistence

import java.util.concurrent.{ExecutorService, Executors, TimeUnit}

import akka.actor.{ActorSystem, Props}

object PersistenceApp extends App{

  val lottery = Lottery(10000, 10000)

  val system = ActorSystem("persistence-system")

  val lotteryActor = system.actorOf(Props(new LotteryActor(lottery)), "Lottery")

  val pool: ExecutorService = Executors.newFixedThreadPool(10)

  val r = (1 to 100).map(i => 
      new LotteryRun(lotteryActor, LotteryCmd(i.toLong, "cmonkey", "cmonkey at gmail.com"))
      )

  r.map(pool.execute(_))

  TimeUnit.SECONDS.sleep(1)

  pool.shutdown()

  system.terminate()

}
