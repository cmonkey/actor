package org.github.cmonkey.actor

object casapp extends App{

  val atomic = new java.util.concurrent.atomic.AtomicInteger(1)

  val isCas = atomic.compareAndSet(1, 5)

  println(s"${isCas}")

  println(atomic.get())

}
