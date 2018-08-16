package org.github.cmonkey.actor

import akka.actor.ActorSystem
import org.github.cmonkey.actor.DeveloperActor.NewFeature

object DeveloperActorApp extends App {
  val system = ActorSystem.create("outsource-company")
  val tester = system.actorOf(TesterActor.props, "sam")

  val developer = system.actorOf(DeveloperActor.props(tester), "bob")

  developer ! NewFeature("social integration")

  system.terminate()
}
