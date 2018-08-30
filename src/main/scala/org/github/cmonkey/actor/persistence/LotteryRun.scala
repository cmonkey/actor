package org.github.cmonkey.actor.persistence

import akka.actor.ActorRef
import akka.util.Timeout

import scala.concurrent.duration._
import akka.actor.Actor.Receive
import akka.actor.{ActorLogging, ActorRef, ActorSystem, Props}
import akka.persistence._
import akka.pattern.ask
import akka.util.Timeout

import scala.collection.mutable.ArrayBuffer
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
class LotteryRun(lotteryActor: ActorRef, lotteryCmd: LotteryCmd) extends Runnable{
  implicit val timeout = Timeout(3.seconds)

  def run: Unit = {
    for{
      fut <- lotteryActor ? lotteryCmd
    }yield fut match {
      case le: LuckyEvent => println("恭喜用户 ${le.userId} 抽到了 ${le.luckyMoney}")
      case fe: FailureEvent => println(fe.reason)
      case _ => println("重新抽取")
    }
  }
}
