package game.card

case class Card(suit: Suit, rank: Rank){
  override def toString: String =
    s"$suit$rank"
}

object Card {
  val every =
    for {
      suit <- Suit.every
      rank <- Rank.every
    } yield Card(suit, rank)

  def areUnique(cards: Seq[Card]): Boolean =
    cards.length == cards.toSet.size

  def areNUnique(cards: Seq[Card], n: Int): Boolean =
    cards.length == n && areUnique(cards)

  val C2 = new Card(new Suit("C"), new Rank(2))
  val C3 = new Card(new Suit("C"), new Rank(3))
  val C4 = new Card(new Suit("C"), new Rank(4))
  val C5 = new Card(new Suit("C"), new Rank(5))
  val C6 = new Card(new Suit("C"), new Rank(6))
  val C7 = new Card(new Suit("C"), new Rank(7))
  val C8 = new Card(new Suit("C"), new Rank(8))
  val C9 = new Card(new Suit("C"), new Rank(9))
  val CT = new Card(new Suit("C"), new Rank(10))
  val CJ = new Card(new Suit("C"), new Rank(11))
  val CQ = new Card(new Suit("C"), new Rank(12))
  val CK = new Card(new Suit("C"), new Rank(13))
  val CA = new Card(new Suit("C"), new Rank(14))
  val D2 = new Card(new Suit("D"), new Rank(2))
  val D3 = new Card(new Suit("D"), new Rank(3))
  val D4 = new Card(new Suit("D"), new Rank(4))
  val D5 = new Card(new Suit("D"), new Rank(5))
  val D6 = new Card(new Suit("D"), new Rank(6))
  val D7 = new Card(new Suit("D"), new Rank(7))
  val D8 = new Card(new Suit("D"), new Rank(8))
  val D9 = new Card(new Suit("D"), new Rank(9))
  val DT = new Card(new Suit("D"), new Rank(10))
  val DJ = new Card(new Suit("D"), new Rank(11))
  val DQ = new Card(new Suit("D"), new Rank(12))
  val DK = new Card(new Suit("D"), new Rank(13))
  val DA = new Card(new Suit("D"), new Rank(14))
  val H2 = new Card(new Suit("H"), new Rank(2))
  val H3 = new Card(new Suit("H"), new Rank(3))
  val H4 = new Card(new Suit("H"), new Rank(4))
  val H5 = new Card(new Suit("H"), new Rank(5))
  val H6 = new Card(new Suit("H"), new Rank(6))
  val H7 = new Card(new Suit("H"), new Rank(7))
  val H8 = new Card(new Suit("H"), new Rank(8))
  val H9 = new Card(new Suit("H"), new Rank(9))
  val HT = new Card(new Suit("H"), new Rank(10))
  val HJ = new Card(new Suit("H"), new Rank(11))
  val HQ = new Card(new Suit("H"), new Rank(12))
  val HK = new Card(new Suit("H"), new Rank(13))
  val HA = new Card(new Suit("H"), new Rank(14))
  val S2 = new Card(new Suit("S"), new Rank(2))
  val S3 = new Card(new Suit("S"), new Rank(3))
  val S4 = new Card(new Suit("S"), new Rank(4))
  val S5 = new Card(new Suit("S"), new Rank(5))
  val S6 = new Card(new Suit("S"), new Rank(6))
  val S7 = new Card(new Suit("S"), new Rank(7))
  val S8 = new Card(new Suit("S"), new Rank(8))
  val S9 = new Card(new Suit("S"), new Rank(9))
  val ST = new Card(new Suit("S"), new Rank(10))
  val SJ = new Card(new Suit("S"), new Rank(11))
  val SQ = new Card(new Suit("S"), new Rank(12))
  val SK = new Card(new Suit("S"), new Rank(13))
  val SA = new Card(new Suit("S"), new Rank(14))
}