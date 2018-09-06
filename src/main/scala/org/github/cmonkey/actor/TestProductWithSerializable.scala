package org.github.cmonkey.actor

import org.github.cmonkey.actor.EnumExample1._

object TestProductWithSerializable extends App{

  val incomplete = Set(Pending,InProgress)

  println(s"incomplete = ${incomplete}")

}
