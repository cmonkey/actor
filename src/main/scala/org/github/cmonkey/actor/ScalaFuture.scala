package org.github.cmonkey.actor

import java.util.concurrent.TimeUnit

import scala.concurrent._
import scala.util.Success

object ScalaFuture extends App{

  implicit def executionContext = ExecutionContext.global

  val f: Future[String] = Future("Hello Future")

  f.onComplete {
    case Success(r) => println(s"future value = ${r}")
    case _ => println("done")
  }

  TimeUnit.SECONDS.sleep(1)

}
