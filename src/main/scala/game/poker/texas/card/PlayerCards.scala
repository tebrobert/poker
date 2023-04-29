package game.poker.texas.card

import game.card.Card

case class PlayerCards(cards: Set[Card])

object PlayerCards{
  def create(cards: Set[Card]): Option[PlayerCards] =
    Option.when(cards.size == 2)(new PlayerCards(cards))
}
