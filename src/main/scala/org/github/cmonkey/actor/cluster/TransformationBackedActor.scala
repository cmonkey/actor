package org.github.cmonkey.actor.cluster

import akka.actor.{Actor, RootActorPath}
import akka.cluster.{Cluster, Member}
import akka.cluster.ClusterEvent.{CurrentClusterState, MemberEvent, MemberUp}
import akka.cluster.MemberStatus


class TransformationBackendActor extends Actor {

  val cluster = Cluster(context.system)

  override def preStart(): Unit = cluster.subscribe(self, classOf[MemberEvent])

  override def postStop(): Unit = cluster.unsubscribe(self)

  def receive = {
    case TransformationJob(text) => {
      val result = text.toUpperCase 
      sender() ! TransformationResult(text.toUpperCase)
    }
    case state: CurrentClusterState => 
      state.members.filter(_.status == MemberStatus.Up) foreach register
    case MemberUp(m) => register(m)
  }

  def register(member:Member): Unit = {
    context.actorSelection(RootActorPath(member.address) / "user" / "frontend")
    BackendRegistration
  }
}
