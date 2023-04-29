package player

case class PlayerCreds(
  nickname: PlayerNickname,
  passwordHash: PasswordHash,
)
