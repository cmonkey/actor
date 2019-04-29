import scala.concurrent.Future

import scala.concurrent.ExecutionContext.Implicits.global

object FutureApp extends App{

  Future{"hi"}

  Future("hi").foreach(z => println(z + " world"))

  def job(n: Int) = Future{
    Thread.sleep(1000)
    println(n)
    n + 1
  }

  val f = for{
    f1 <- job(1)
    f2 <- job(f1)
    f3 <- job(f2)
    f4 <- job(f3)
    f5 <- job(f4)
  } yield List(f1, f2, f3, f4, f5)

  f.map (z => println(s"Done, ${z.size} jobs run"))

  val fParallel = for{
    f1 <- job(1)
    f2 <- Future.sequence(List(job(f1), job(f1)))
    f3 <- job(f2.head)
    f4 <- Future.sequence(List(job(f3), job(f3)))
    f5 <- job(f4.head)
  } yield f2.size + f4.size

  fParallel.foreach(z => println(s" Done, $z jobs run in parallel"))

  Thread.sleep(6000)
}
