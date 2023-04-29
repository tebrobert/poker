package money

import MoneyType.MoneyType

case class Balance private (private val value: MoneyType) extends AnyVal {
//  def withdraw(amount: MoneyType): Option[(Balance, PlayerStack)] = {
////    Option.when(0 < amount && amount <= value)(
////      (new Balance(value - amount), new PlayerStack(amount))
////    )
//    for {
//      playerStack <- PlayerStack(amount) if amount <= value
//    } yield (new Balance(value - amount), playerStack)
//  }
}

object Balance {
  def apply(value: MoneyType): Option[Balance] =
    Option.when(value >= 0)(new Balance(value))
}