package web

import cats._
import cats.effect._
import cats.implicits._
import com.comcast.ip4s._
import org.http4s.ember.server.EmberServerBuilder
import org.http4s.implicits._
import org.http4s.server.middleware.Logger
import web.Types._

object Main extends IOApp.Simple {
  def program[F[+_] : Async : Monad]: F[Nothing] = {
    val service = Service.impl[F]

    val httpApp = (
      Routes.serviceRoutes[F](service)
      ).orNotFound

    val finalHttpApp =
      Logger.httpApp(logHeaders = true, logBody = true)(httpApp)

    for {
      _ <- service.createTables
      _ <- Ref.empty[F, Map[Nickname, AuthToken]]
      server <- EmberServerBuilder.default[F]
        .withHost(ipv4"0.0.0.0")
        .withPort(port"5432") // using port exposed by postgres docker image
        // postgres itself is shifted on another port
        .withHttpApp(finalHttpApp)
        .build
        .useForever
    } yield server
  }

  val run = program[IO]
}
