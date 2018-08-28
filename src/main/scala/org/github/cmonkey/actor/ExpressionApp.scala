package org.github.cmonkey.actor

object ExpressionApp extends  App{

  def exp(flag: Boolean, exp1: => Unit, exp2: => Unit): Unit ={
    if(flag){
      exp1
    }else{
      exp2
    }
  }

  def exp1(flag: Boolean, exp1:() => Unit, exp2: () => Unit) = {
    if(flag){
      exp1
    }else{
      exp2
    }
  }

  exp(true, println("test 1"), println("test 2"))
  exp1(true, () => println("test 1"), () => println("test 2"))


  exp(false, println("test 1"), println("test 2"))
  exp1(false, () => println("test 1"), () => println("test 2"))

  //exp(true, (println("test 1");println("test 2)), println("test 2"))

}
