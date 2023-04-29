package game.card

case class Rank(value: Int) extends AnyVal {
  override def toString: String =
    value match {
      case x if 2 to 9 contains x => x.toString
      case 10 => "T"
      case 11 => "J"
      case 12 => "Q"
      case 13 => "K"
      case 14 => "A"
    }
}

object Rank {
  val every = (2 to 14).map(new Rank(_))

  def apply(value: Int): Option[Rank] =
    Option.when(every.map(_.value) contains value)(new Rank(value))

  def compare(left: Rank, right: Rank): Int =
    left.value - right.value
}