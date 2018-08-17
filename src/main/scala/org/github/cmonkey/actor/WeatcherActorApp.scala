package org.github.cmonkey.actor

import akka.actor.{ActorSystem, Props}

import scala.io.Source
import scala.xml.XML

object WeatcherActorApp extends App{

  def getWeatherInfo(id: String) = {
    val url = "http://weather.yahooapis.com/forecastrss?w=" + id + "&u=c"

    val response = Source.fromURL(url).mkString
    val xmlResponse = XML.loadString(response)

    println(xmlResponse \\ "location" \\ "@city",

    xmlResponse \\ "condition" \\ "@temp")
  }

  val system = ActorSystem("WeatherSystem")

  val weatherActor = system.actorOf(Props[WeatcherActor], name = "weatheractor")

  val start = System.nanoTime

  for (id <- 4118 to 4128) { //WOEID - Where On Earth ID
    weatherActor ! getWeatherInfo(id.toString())
  }

  val end = System.nanoTime
  println("Time : " + (end - start) / 1000000000.0)

}
