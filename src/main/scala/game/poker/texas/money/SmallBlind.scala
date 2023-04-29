package game.poker.texas.money

import money.MoneyType.MoneyType

//case class SmallBlind(bet: MoneyType) extends AnyVal
object SmallBlind {
  type SmallBlind = MoneyType

  def apply(money: MoneyType): SmallBlind =
    money
}
