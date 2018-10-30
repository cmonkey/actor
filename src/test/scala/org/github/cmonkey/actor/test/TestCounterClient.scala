package org.github.cmonkey.actor.test

import java.util.concurrent.{Executors, TimeUnit}

import org.github.cmonkey.actor.counter._

object TestCounterClient extends App{

  val num_of_threads = 1000
  val num_of_increments = 100000

  val executeService = Executors.newFixedThreadPool(num_of_threads)

  //val counter = new StupidCounter()
  //val counter = new SyncCounter()
  //val counter = new LockCounter
  //val counter = new AtomicCounter
  val counter = new CASCounter

  val before = System.currentTimeMillis()

  (0 to num_of_threads).foreach(i => {
    executeService.submit(new CounterClient(num_of_increments, counter))
  })

  executeService.shutdown()
  executeService.awaitTermination(1, TimeUnit.MINUTES)

  val after = System.currentTimeMillis()

  println(s"counter result = ${counter.getCounter()}")
  println(s"Time passed in ms: = ${after - before}")

}
