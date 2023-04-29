package game.poker.texas.money

import money.MoneyType.MoneyType

case class Stack(value: MoneyType) extends AnyVal

object Stack {
  def fromMoney(value: MoneyType): Option[Stack] =
    Option.when(0 < value)(new Stack(value))

  val initial: Stack = new Stack(1_000)
}
