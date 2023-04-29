package player

import money.Balance

case class PlayerProfile(
  id: PlayerId,
  nickname: PlayerNickname,
  balance: Balance,
)
