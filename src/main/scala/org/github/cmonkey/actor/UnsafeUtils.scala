package org.github.cmonkey.actor

import sun.misc.Unsafe

class UnsafeUtils {

  def getUnsafe(): Unsafe = {

    val field = classOf[Unsafe].getDeclaredField("theUnsafe")
    field.setAccessible(true)

    field.get(null).asInstanceOf[Unsafe]
  }
}

object UnsafeUtils{
  val utils = new UnsafeUtils

  def getUnsafe() = utils.getUnsafe()
}
