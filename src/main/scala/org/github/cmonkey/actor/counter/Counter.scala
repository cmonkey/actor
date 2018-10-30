package org.github.cmonkey.actor.counter

trait Counter{

  def increment(): Unit

  def getCounter(): Long
}
