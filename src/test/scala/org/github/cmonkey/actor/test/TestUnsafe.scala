package org.github.cmonkey.actor.test

import java.lang.reflect.Field

import sun.misc.Unsafe

object TestUnsafe extends App{

  val theUnsafeField:Field = classOf[Unsafe].getDeclaredField("theUnsafe")
  println(theUnsafeField)
  theUnsafeField.setAccessible(true)
  val unsafe:Unsafe = theUnsafeField.get(null).asInstanceOf[Unsafe]
  println(unsafe)

}
