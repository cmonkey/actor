package org.github.cmonkey.actor.counter

import java.util.concurrent.locks.ReentrantReadWriteLock

class LockCounter extends Counter{
  val readWriteLock = new ReentrantReadWriteLock()
  val lock = readWriteLock.writeLock()
  var counter: Long = 0L
  override def increment(): Unit = {
    lock.lock()
    counter += 1
    lock.unlock()
  }

  override def getCounter(): Long = {
    counter
  }
}
