package org.github.cmonkey.actor

import java.nio.file.Paths

import akka.actor.ActorSystem


object FolderScannerActorApp extends App {
  val system = ActorSystem.create("file-reader")
  val scanner = system.actorOf(FolderScannerActor.props, "scanner")
  val directoryPath = Paths.get("/tmp").toString

  scanner ! directoryPath

  system.terminate()
}
