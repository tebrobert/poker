package web

import cats.data.EitherT
import cats.effect.Sync
import cats.implicits._
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl
import web.Types._

object Routes {

  def serviceRoutes[F[_]: Sync](service: Service[F]): HttpRoutes[F] = {
    val dsl = new Http4sDsl[F]{}
    import dsl._
    HttpRoutes.of[F] {
      case GET -> Root / "auth" / nickname / passwordHash => (
        service.auth(nickname, passwordHash).map(_.toString)
        >>= (Ok.apply(_))
      )

      case GET -> Root / token / "status" => (
        (for {
          nickname <- EitherT[F, ErrorMessage, Nickname](service.checkToken(token))
        } yield s"$nickname, not playing")
          .value.map(_.merge)
        >>= (Ok.apply(_))
      )

      case e => Ok(e.uri.toString)
    }
  }

}