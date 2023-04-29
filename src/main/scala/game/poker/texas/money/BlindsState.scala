package game.poker.texas.money

import game.poker.texas.money.BigBlind.BigBlind
import game.poker.texas.money.BlindsState.presets
import game.poker.texas.money.GamePrestige.GamePrestige
import game.poker.texas.money.SmallBlind.SmallBlind
import money.MoneyType.MoneyType

import scala.math.pow

case class BlindsState(
  gamePrestige: GamePrestige,
  numberOfIncreasingBlinds: Int,
) {
  def getBlinds: (SmallBlind, BigBlind) = {
    val presetNum = numberOfIncreasingBlinds % presets.length
    val order = numberOfIncreasingBlinds / presets.length
    val adjustFactor = (_: MoneyType) * pow(10, order + gamePrestige)
    val (small, big) = presets(presetNum)
    (adjustFactor(small), adjustFactor(big))
  }
}

object BlindsState {
  val presets = Seq(
    (SmallBlind(1), BigBlind(2)),
    (SmallBlind(2), BigBlind(4)),
    (SmallBlind(3), BigBlind(6)),
    (SmallBlind(5), BigBlind(10)),
  )
}