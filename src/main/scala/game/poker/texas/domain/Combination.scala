package game.poker.texas.domain

import game.card.Card

case class Combination(cards: Set[Card])

object Combination {
  def fromSet(cards: Set[Card]): Option[Combination] =
    Option.when(cards.size == 5)(new Combination(cards))
}