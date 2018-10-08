package org.github.cmonkey.actor.backPressure

import java.math.{MathContext, RoundingMode}

import akka.NotUsed
import akka.actor.ActorSystem
import akka.stream.scaladsl.{Balance, Flow, GraphDSL, Keep, Merge, Sink, Source}
import akka.stream.{ActorMaterializer, FlowShape, Materializer}

import scala.concurrent.Await

object ManagerActorStreamApp extends  App{

  implicit val actorSystem: ActorSystem = ActorSystem("test")
  implicit val materializer: Materializer = ActorMaterializer()

  val mc = new MathContext(100, RoundingMode.HALF_EVEN)
  val plus = BigDecimal(1, mc)
  val minus = BigDecimal(-1, mc)

  val producer: Source[Int, NotUsed] = Source.fromIterator(() => Iterator.range(1, 1000000))

  val consumers: Flow[Int, BigDecimal, NotUsed] = Flow.fromGraph(GraphDSL.create() { implicit builder =>
    import GraphDSL.Implicits._
    val balancer = builder.add(Balance[Int](8))
    val worker = Flow[Int].map { data =>
      val sign = if ((data & 1) == 1) plus else minus
      val result = sign / data
      result
    }

    val merge = builder.add(Merge[BigDecimal](8))
    val workers = List.fill(8)(worker)

    workers.foreach(f =>
      balancer ~> f ~> merge
    )
    FlowShape(balancer.in, merge.out)
  })

  val resultAggregator = Sink.fold[BigDecimal, BigDecimal](BigDecimal(0, mc))(_ + _)

  val runnableGraph = producer.via(consumers).toMat(resultAggregator)(Keep.right)

  val start = System.currentTimeMillis()
  val futureResult = runnableGraph.run()

  import scala.concurrent.duration._

  val approximation = Await.result(futureResult, 1.minute)

  println(s"final result: $approximation, time ${System.currentTimeMillis() - start}ms")
  actorSystem.terminate()
}
