package game.poker.texas.state

import game.card.{Card, Deck}

sealed trait BoardCardsState

final case class PreFlop(deck: Deck) extends BoardCardsState {
  def takeFlop: Option[Flop] =
    deck.take(3)
      .map { case (next, rest) => Flop(next, rest) }
}

final case class Flop(cards: Seq[Card], deck: Deck) extends BoardCardsState {
  def takeTurn: Option[Turn] =
    deck.take(1)
      .map { case (next, rest) => Turn(cards ++ next, rest) }
}

final case class Turn(cards: Seq[Card], deck: Deck) extends BoardCardsState {
  def takeRiver: Option[River] =
    deck.take(1)
      .map { case (next, _) => River(cards ++ next) }
}

final case class River(cards: Seq[Card])
  extends BoardCardsState
