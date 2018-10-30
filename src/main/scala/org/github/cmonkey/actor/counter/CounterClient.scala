package org.github.cmonkey.actor.counter

class CounterClient(num: Int, counter: Counter) extends Runnable{

  override def run(): Unit = {
    (0 to num).foreach(i => {
      counter.increment()
    })
  }
}
