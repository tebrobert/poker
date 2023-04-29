package game.domain

case class Seed(value: Long){
  def shuffled[A](list: Seq[A]): Seq[A] =
    new util.Random(value).shuffle(list)
}