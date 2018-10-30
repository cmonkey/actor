package org.github.cmonkey.actor.counter

class StupidCounter extends Counter {
  var counter:Long = 0L
  override def increment(): Unit = {
    counter += 1
  }

  override def getCounter(): Long = {
    counter
  }
}
