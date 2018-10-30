package org.github.cmonkey.actor.counter

import org.github.cmonkey.actor.UnsafeUtils

class CASCounter extends Counter{
  @volatile var counter = 0L
  val unsafe = UnsafeUtils.getUnsafe()

  var offset = unsafe.objectFieldOffset(classOf[CASCounter].getDeclaredField("counter"))
  override def increment(): Unit = {
    var before = counter

    while(!unsafe.compareAndSwapLong(this,offset,before, before + 1)){
      before = counter
    }
  }

  override def getCounter(): Long = {
    counter
  }
}
