import game.poker.texas.money.BlindsState
import org.scalatest.funsuite.AnyFunSuite

class BlindsState extends AnyFunSuite {
  test("0, 0")(assert(BlindsState(0, 0).getBlinds == (1, 2)))
  test("0, 1")(assert(BlindsState(0, 1).getBlinds == (2, 4)))
  test("0, 2")(assert(BlindsState(0, 2).getBlinds == (3, 6)))
  test("0, 3")(assert(BlindsState(0, 3).getBlinds == (5, 10)))
  test("0, 4")(assert(BlindsState(0, 4).getBlinds == (10, 20)))
  test("0, 5")(assert(BlindsState(0, 5).getBlinds == (20, 40)))
  test("0, 6")(assert(BlindsState(0, 6).getBlinds == (30, 60)))
  test("0, 7")(assert(BlindsState(0, 7).getBlinds == (50, 100)))
  test("0, 8")(assert(BlindsState(0, 8).getBlinds == (100, 200)))

  test("1, 0")(assert(BlindsState(1, 0).getBlinds == (10, 20)))
  test("1, 1")(assert(BlindsState(1, 1).getBlinds == (20, 40)))
  test("1, 2")(assert(BlindsState(1, 2).getBlinds == (30, 60)))
  test("1, 3")(assert(BlindsState(1, 3).getBlinds == (50, 100)))
  test("1, 4")(assert(BlindsState(1, 4).getBlinds == (100, 200)))
  test("1, 5")(assert(BlindsState(1, 5).getBlinds == (200, 400)))
  test("1, 6")(assert(BlindsState(1, 6).getBlinds == (300, 600)))
  test("1, 7")(assert(BlindsState(1, 7).getBlinds == (500, 1000)))
  test("1, 8")(assert(BlindsState(1, 8).getBlinds == (1000, 2000)))
}