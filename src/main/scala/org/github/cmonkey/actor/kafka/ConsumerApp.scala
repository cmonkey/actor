package org.github.cmonkey.actor.kafka

import java.util
import java.util.Properties

import org.apache.kafka.clients.consumer.{ConsumerConfig, ConsumerRecord, ConsumerRecords, KafkaConsumer}
import org.apache.kafka.common.TopicPartition
import org.apache.kafka.common.serialization.{StringDeserializer, StringSerializer}

import scala.collection.JavaConverters

object ConsumerApp extends App{

  val consumer = new Consumer(topic = "test", broker = "localhost:9092")

  consumer.receiveMsg()

}

class Consumer(topic: String, broker:String) {

  val configuration: Properties = {
    val props = new Properties()

    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, broker)

    val serializer = classOf[StringDeserializer].getCanonicalName
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, serializer)
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, serializer)
    props.put(ConsumerConfig.GROUP_ID_CONFIG, "1")

    props
  }

  val consumer = new KafkaConsumer[String, String](configuration)
  val subscribers = List[String](topic)
  val topicPartition = new TopicPartition(topic, 0)

  consumer.assign(util.Arrays.asList(topicPartition))

  consumer.seek(topicPartition, 0)
  //consumer.subscribe(JavaConverters.asJavaCollection(subscribers))

  var isPoll = true

  def isPollMsg(): Boolean = {
    isPoll
  }

  def receiveMsg(): Unit = {

    while(isPollMsg()) {
      val records: ConsumerRecords[String, String] = consumer.poll(1000)

      records.forEach(record => {
        println(s"record mesg = ${record}")
      })
    }
  }

  def shudownPollMsg(): Unit = {
    isPoll = false
  }
}
