package org.github.cmonkey.actor

object ThreadLocalApp extends App{

  val threadLocal = new ThreadLocal[String] {

      override def initialValue() = {
        "Hello"
      }
    }


    val user = new User
    val account = new Account

    user.printThreadLocal()
    account.printThreadLocal()

}

class User{
  def printThreadLocal(): Unit = {
    println(s"${ThreadLocalApp.threadLocal.get()}");
  }
}

class Account{
  def printThreadLocal(): Unit = {
    println(s"${ThreadLocalApp.threadLocal.get()}");
  }
}
