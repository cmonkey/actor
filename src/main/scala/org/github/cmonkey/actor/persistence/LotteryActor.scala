package org.github.cmonkey.actor.persistence

import akka.actor.ActorLogging
import akka.persistence.{PersistentActor, RecoveryCompleted, SaveSnapshotSuccess, SnapshotOffer}

class LotteryActor(initState: Lottery) extends PersistentActor with ActorLogging{
  override def persistenceId: String = "lottery-actor-1"

  var state = initState 

  override def receiveRecover: Receive = {
    case event: LuckyEvent => 
      updateState(event)

    case SnapshotOffer(_, snapshot: Lottery) => 
      log.info("Recover actor state from snapshot and the snapshot is ${snapshot}")
      state = snapshot

    case RecoveryCompleted => log.info("the actor recover completed")
  }

  def updateState(le: LuckyEvent) = 
    state = state.update(le.luckyMoney)

  override def receiveCommand: Receive = {
    case lc: LotteryCmd => 
      doLottery(lc) match {
        case le: LuckyEvent => 
          persist(le){event => 
            updateState(event)
            increaseEvtCountAndSnapshot()
            sender() ! event
          }
        case fe: FailureEvent => 
          sender() ! fe
      }

      case "saveSnapshot" => 
        saveSnapshot(state)

      case SaveSnapshotSuccess(metadata) => println("snapshot save success")
  }

  private def increaseEvtCountAndSnapshot() = {
    val snapShotInterval = 5
    if (lastSequenceNr % snapShotInterval == 0 && lastSequenceNr != 0){
      self ! "saveSnapshot"
    }
  }

  def doLottery(lc: LotteryCmd) = {
    if(state.remainAmount > 0){
      val luckyMoney = scala.util.Random.nextInt(state.remainAmount) + 1
      LuckyEvent(lc.userId, luckyMoney)
    }else{
    FailureEvent(lc.userId, "eeeeeeeeeeee")}
  }
}
