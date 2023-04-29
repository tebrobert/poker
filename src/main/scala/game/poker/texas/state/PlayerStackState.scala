package game.poker.texas.state

import game.poker.texas.money.Stack
import player.PlayerId

case class PlayerStackState(
  playerId: PlayerId,
  stack: Stack,
)
