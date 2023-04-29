package money

object MoneyType {
  type MoneyType = BigDecimal
}

// trait MoneyTrait
//
//case class Money protected[money] (protected[money] val value: MoneyType)
//  extends AnyVal with MoneyTrait
//
//object Money {
//  def apply(value: MoneyType): Option[Money] =
//    Option.when(value >= 0)(new Money(value))
//}