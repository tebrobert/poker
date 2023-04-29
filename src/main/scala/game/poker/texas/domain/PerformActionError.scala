package game.poker.texas.domain

import game.poker.texas.money.Bet.Bet

sealed trait PerformActionError

final case object PlayerHasNoSuchMoney extends PerformActionError
final case class RaisingBetShouldBeAtLeast(value: Bet) extends PerformActionError
