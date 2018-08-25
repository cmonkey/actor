package org.github.cmonkey.actor.kafka

import java.util.Properties
import java.util.concurrent.TimeUnit

import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.{KafkaStreams, StreamsBuilder, StreamsConfig}

object StreamApp extends App{

  val stream = new Stream(topic = "test", broker = "slave1:9092,slave2:9092,slave3:9092")

  stream.executeStream()

}

class Stream(topic:String, broker:String){
  val configuration: Properties = {

    val props = new Properties()

    props.put(StreamsConfig.APPLICATION_ID_CONFIG, "stream-id-config")
    props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, broker)
    props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass)
    props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass)

    props
  }

  def executeStream() = {

    val builder = new StreamsBuilder

    val kStream = builder.stream[String, String](topic)

    val lStream = kStream.mapValues(record => record.length + "!")

    lStream.to(topic)

    val stream = new KafkaStreams(builder.build(), configuration)

    stream.start()

    sys.addShutdownHook(
      stream.close(10, TimeUnit.SECONDS)
    )
  }

}
