package org.github.cmonkey.actor.kafka

import java.util.Properties

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}
import org.apache.kafka.common.serialization.StringSerializer

object ProducerApp extends App{

  val producer = new Producer(topic = "test", broker = "slave1:9092,slave2:9092,slave3:9003")

  val msgList = List("a", "b", "c", "d")

  Range(0, 100000000).foreach(item => {

    producer.sendMesg(msgList)
  })

  producer.close()

}

class Producer(topic: String, broker:String) {

  val configuration:Properties = {
    val props = new Properties()

    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, broker)

    val serializer = classOf[StringSerializer].getCanonicalName

    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, serializer)
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, serializer)

    props
  }

  val producer = new KafkaProducer[String, String](configuration)

  def sendMesg(msgs: List[String]) = {

    msgs.foreach(msg => {
      val record = new ProducerRecord[String, String](topic, "1", msg)

      producer.send(record)
    })

  }

  def close() = {
    producer.close()
  }
}
