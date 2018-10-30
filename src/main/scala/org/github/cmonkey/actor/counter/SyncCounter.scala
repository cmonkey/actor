package org.github.cmonkey.actor.counter

class SyncCounter extends Counter {
  var counter:Long = 0L

  override def increment(): Unit = this.synchronized({
    counter += 1
  })

  override def getCounter(): Long = {
    counter
  }
}
