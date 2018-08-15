import akka.actor.{ActorSystem, Props}

object SupervisingActorApp extends App {
  val system = ActorSystem("supervisingSystem")

  val supervisingActor = system.actorOf(Props[SupervisingActor], "supervising-actor")

  supervisingActor ! "FailChild"

  system.terminate()
}
