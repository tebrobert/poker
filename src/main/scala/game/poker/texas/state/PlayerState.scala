package game.poker.texas.state

import cats.data.NonEmptyList
import cats.implicits._
import game.card.Deck
import game.poker.texas.card.PlayerCards
import game.poker.texas.money.Bet.Bet
import game.poker.texas.money.{Bet, Stack}
import player.PlayerId

case class PlayerState(
  playerStackState: PlayerStackState,
  optionalCards: Option[PlayerCards],
  totalBets: Bet,
) {
  def playerId: PlayerId =
    playerStackState.playerId

  def stack: Stack =
    playerStackState.stack
}

object PlayerState {
  def fromPlayersCards(
    playersStackStates: List[PlayerStackState],
    playersCards: List[PlayerCards],
  ): Option[NonEmptyList[PlayerState]] =
    NonEmptyList.fromList(
      playersStackStates
        .zip(playersCards)
        .map { case (playerStackState, playerCards) =>
          PlayerState(
            playerStackState,
            playerCards.some,
            0
          )
        }
    )

  def fromDeck(
    playersStackStates: List[PlayerStackState],
    deck: Deck,
  ): Option[(NonEmptyList[PlayerState], Deck)] = {
    val (firstCards, restCards) =
      deck.cards.splitAt(playersStackStates.size * 2)

    for {
      playersCards <- firstCards.grouped(2)
        .map(_.toSet).map(PlayerCards.create).toList.sequence

      playersGameStates <- fromPlayersCards(
        playersStackStates, playersCards
      )

      restDeck <- Deck.create(restCards)

    } yield (playersGameStates, restDeck)
  }
}