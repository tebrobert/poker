package game.poker.texas.state

import cats.data.NonEmptyList
import game.card.Deck
import game.domain.Seed
import game.poker.texas.domain.{Call, Fold, PerformActionError, PlayerAction, Raise}
import game.poker.texas.money.BlindsState
import game.poker.texas.money.GamePrestige.GamePrestige
import player.PlayerId

// todo: should be tested
case class RoundState(
  turn: PlayerId,
  playersStates: NonEmptyList[PlayerState],
  boardMoneyState: BoardMoneyState,
  boardCardsState: BoardCardsState,
) {
  def perform(action: PlayerAction): Either[PerformActionError, RoundState] =
    action match {
      case Fold => ???
      case Call => ???
      case Raise(bet) => ???
    }
}

object RoundState {
  def create(
    seed: Seed,
    playersStackStates: NonEmptyList[PlayerStackState],
    gamePrestige: GamePrestige
  ): Option[RoundState] = {
    val deck = Deck.shuffled(seed)

    for {
      (playersGameStates, restDeck) <-
        PlayerState.fromDeck(playersStackStates.toList, deck)

      blindsState = BlindsState(gamePrestige, 0)
      bigBlind = blindsState.getBlinds._2

    } yield RoundState(
      playersStackStates.head.playerId,
      playersGameStates,
      BoardMoneyState.initial(bigBlind),
      PreFlop(restDeck),
    )
  }
}
