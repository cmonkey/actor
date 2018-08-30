package org.github.cmonkey.actor.robot

case class TurnOnLight(time: Int) extends Action {

  override val message: String =  "Turn on the living room light"

}
