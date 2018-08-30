package org.github.cmonkey.actor.persistence

case class Lottery(totalAmount: Int, remainAmount: Int){

  def update(luckyMoney: Int) = {
    copy(
      remainAmount = remainAmount - luckyMoney
      )
  }
}
