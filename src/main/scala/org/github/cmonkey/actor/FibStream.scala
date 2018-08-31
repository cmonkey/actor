package org.github.cmonkey.actor

object FibStream extends App{

  def fib(x: Long, y: Long): Stream[Long] = {

    x #:: fib(x, x + y)
  }

  fib(1, 1).take(100000)

}
