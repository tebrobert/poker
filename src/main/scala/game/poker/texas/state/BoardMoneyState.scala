package game.poker.texas.state

import game.poker.texas.money.Bank.Bank
import game.poker.texas.money.Bet.Bet
import game.poker.texas.money.BetDelta.BetDelta
import game.poker.texas.money.BigBlind.BigBlind
import game.poker.texas.money.{Bank, Bet, BetDelta, BigBlind}
import money.MoneyType.MoneyType

case class BoardMoneyState(
  bigBlind: BigBlind,
  lastBetGrowth: BetDelta,
  bet: Bet,
  bank: Bank,
)

object BoardMoneyState {
  def initial(bigBlind: MoneyType): BoardMoneyState =
    new BoardMoneyState(
      bigBlind,
      bigBlind,
      bigBlind,
      0
    )
}