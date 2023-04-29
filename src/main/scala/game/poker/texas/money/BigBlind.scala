package game.poker.texas.money

import money.MoneyType.MoneyType

//case class BigBlind(bet: MoneyType) extends AnyVal
object BigBlind {
  type BigBlind = MoneyType

  def apply(money: MoneyType): BigBlind =
    money
}
