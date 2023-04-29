package game.poker.texas.domain

import game.poker.texas.money.Bet
import game.poker.texas.money.Bet.Bet

sealed trait PlayerAction

final case object Fold extends PlayerAction
final case object Call extends PlayerAction
final case class Raise(bet: Bet) extends PlayerAction
