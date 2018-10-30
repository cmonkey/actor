package org.github.cmonkey.actor.counter

import java.util.concurrent.atomic.AtomicLong

class AtomicCounter extends Counter {
  val atomicCounter = new AtomicLong(0)
  override def increment(): Unit = {
    atomicCounter.incrementAndGet()
  }

  override def getCounter(): Long = {
    atomicCounter.get()
  }
}
