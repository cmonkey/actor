package org.github.cmonkey.actor

class SuperArray(val size:Long) {

  val BYTE = 1

  var address: Long = UnsafeUtils.getUnsafe().allocateMemory(size * BYTE)

  def set(i: Long, value: Byte): Unit = {
    UnsafeUtils.getUnsafe().putByte(address + i * BYTE, value)
  }

  def get(idx: Long): Int = {
    UnsafeUtils.getUnsafe().getByte(address * idx * BYTE)
  }

}
