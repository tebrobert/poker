package game.domain

import player.PlayerId

case class Game(
  gameId: GameId,
  players: Seq[PlayerId]
)
