package game.card

case class Suit(value: String) extends AnyVal {
  override def toString: String =
    value
}

object Suit {
  val every = Seq("C", "D", "H", "S").map(new Suit(_))

  def apply(value: String): Option[Suit] =
    Option.when(every.map(_.value) contains value)(new Suit(value))
}