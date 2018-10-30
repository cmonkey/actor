package org.github.cmonkey.actor.test

import org.github.cmonkey.actor.SuperArray

object TestSuperArray extends App{

  val superSize: Long  = Integer.MAX_VALUE

  val superArray= new SuperArray(superSize)

  println(s"array size = ${superArray.size}")

  var sum = 0
  val num = 10
  (1 to 100).foreach(i => {
    superArray.set(num + i, 3.toByte)
    sum += superArray.get(num + i)
  })

  println(s"sum = ${sum}")

}
