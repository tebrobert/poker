package web

import doobie.Fragment
import doobie.implicits._

final class QueryInterpolator(private val sc: StringContext) extends AnyVal {
  def q(args: Any*): Fragment =
    new StringContext(sc.s(args:_*)).sql()
}

trait ToQueryInterpolator {
  implicit def toQueryInterpolator(sc: StringContext): QueryInterpolator =
    new QueryInterpolator(sc)
}

object string extends ToQueryInterpolator
