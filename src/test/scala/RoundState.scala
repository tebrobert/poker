import cats.data.NonEmptyList
import cats.implicits._
import game.card.Card._
import game.card.{Card, Deck}
import game.domain.Seed
import game.poker.texas.card.PlayerCards
import game.poker.texas.money.Stack
import game.poker.texas.state._
import org.scalatest.funsuite.AnyFunSuite
import player.PlayerId

class RoundState extends AnyFunSuite {
  def ps(playerId: Long, card1: Card, card2: Card): PlayerState =
    PlayerState(
      PlayerStackState(PlayerId(playerId), Stack(1000)),
      Some(PlayerCards(Set(card1, card2))), 0
    )

  test("1") {
    val seed = Seed(1)
    val playersIds = NonEmptyList.of(11, 12, 13, 14, 15, 16).map(PlayerId(_))
    val playersStackStates = playersIds.map {
      PlayerStackState(_, Stack.initial)
    }
    val gamePrestige = 0
    val actual = RoundState.create(seed, playersStackStates, gamePrestige)

    val expected = RoundState(
      PlayerId(11),
      NonEmptyList.of(
        ps(11, S7, H2), ps(12, D5, H8), ps(13, H4, H5),
        ps(14, DK, H9), ps(15, DA, S3), ps(16, CJ, S2),
      ),
      BoardMoneyState(2, 2, 2, 0),
      PreFlop(Deck(List(
        CK, HJ, S4, C8, S8, C7, D4, SJ, DQ, D2, SQ, S5, H6, HQ,
        SA, HA, CT, SK, C5, S9, C4, DJ, C9, D9, H3, S6, D7, HK, H7, D8, DT, HT,
        C3, C6, CA, D3, C2, ST, CQ, D6
      )))
    ).some

    assert(actual == expected)
  }
}