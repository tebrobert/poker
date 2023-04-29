package game.card

import Card.areUnique
import game.domain.Seed

case class Deck(cards: Seq[Card]) {
  def take(n: Int): Option[(Seq[Card], Deck)] = {
    Option.when(n <= cards.size)(cards.splitAt(n))
      .map { case (taken, rest) => (taken, new Deck(rest)) }
  }
}

object Deck {
  val deckSize = Card.every.size

  def create(cards: Seq[Card]): Option[Deck] =
    Option.when(areUnique(cards))(new Deck(cards))

  def shuffled(seed: Seed): Deck =
    new Deck(seed.shuffled(Card.every))
}
