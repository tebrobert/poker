package web

import cats.Applicative
import cats.effect.Async
import cats.implicits._
import cats._
import doobie.Transactor
import doobie.implicits._
import web.Types._
import web.string.toQueryInterpolator

trait Service[F[_]] {
  def createTables: F[Unit]

  def auth(nickname: Nickname, passwordHash: PasswordHash)
  : F[Either[ErrorMessage, AuthToken]]

  def checkToken(token: AuthToken): F[Either[ErrorMessage, Nickname]]
}

object Service {
  def apply[F[_]](implicit ev: Service[F]): Service[F] = ev

  def impl[F[_] : Applicative : Async : Monad]: Service[F] = new Service[F] {
    val dbCreds = Transactor.fromDriverManager[F](
      "org.postgresql.Driver",
      "jdbc:postgresql://0.0.0.0:5555/postgres",
      "postgres",
      "Stt-6789",
    )

    val tableUSERS = "USERS"
    val colNickname = "nickname"
    val colPasswordHash = "passwordHash"
    val colBalance = "balance"

    def createTables: F[Unit] =
      q"""create table if not exists
        $tableUSERS(
           id SERIAL PRIMARY KEY,
           $colNickname varchar(20),
           $colPasswordHash varchar(128),
           $colBalance int
        )""".update.run.transact(dbCreds).as()

    def lookUpPasswordHashByNickname(nickname: Nickname): F[Option[PasswordHash]] =
      q"""select $colPasswordHash
        from $tableUSERS
        where $colNickname = '$nickname'
        """.query[PasswordHash].option.transact(dbCreds)

    def doCreateUser(nickname: Nickname, passwordHash: PasswordHash): F[Unit] =
      q"""insert into $tableUSERS
        ($colNickname, $colPasswordHash, $colBalance)
        values
        ('$nickname', '$passwordHash', 10000)
        """.update.run.transact(dbCreds).as()

    def doAuthorize(nickname: Nickname): F[AuthToken] =
      nickname.+("token").pure[F] // TODO IMPLEMENT

    def auth(nickname: Nickname, passwordHash: PasswordHash)
    : F[Either[ErrorMessage, AuthToken]] =
      for {
        optionalExistingPasswordHash <-
          lookUpPasswordHashByNickname(nickname)

        result <- optionalExistingPasswordHash
          .fold {
            doCreateUser(nickname, passwordHash) >>
              doAuthorize(nickname).map(Either.right[ErrorMessage, AuthToken])
          } {
            existingPasswordHash =>
              if (existingPasswordHash == passwordHash)
                doAuthorize(nickname).map(Either.right[ErrorMessage, AuthToken])
              else (Either.left[ErrorMessage, AuthToken]("Bad password").pure[F])
          }
      } yield result

    def checkToken(token: AuthToken): F[Either[ErrorMessage, Nickname]] =
    // todo implement
      if (token.endsWith("token"))
        Either.right[ErrorMessage, Nickname](token.dropRight("token".length)).pure[F]
      else
        Either.left[ErrorMessage, Nickname]("Bad token").pure[F]
  }
}
