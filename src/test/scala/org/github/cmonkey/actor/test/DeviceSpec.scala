package org.github.cmonkey.actor.test

import akka.actor.ActorSystem
import akka.testkit.{TestKit, TestProbe}
import org.github.cmonkey.actor.Device

class DeviceSpec extends TestKit(ActorSystem("deviceSpec")) {

  /*
  "device" should "reply with empty reading if no temperature is known" in {
    val probe = TestProbe()

    val deviceActor = system.actorOf(Device.props("group", "device"))

    deviceActor.tell(Device.ReadTemperature(requestId = 42), probe.ref)

    val response = probe.expectMsgType[Device.RespondTemperature]
    response.requestId should ===(42)
    response.value should ===(None)
  }
  */
}
