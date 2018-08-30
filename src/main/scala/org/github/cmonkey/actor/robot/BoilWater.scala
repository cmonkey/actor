package org.github.cmonkey.actor.robot

case class BoilWater(time: Int) extends Action {
  override val message: String = "Burn a pot of water"
}
