import org.specs2.mutable._
import scala.collection.immutable.Stack

class HanoiSpec extends Specification {
  object ThreeDisks extends Solver {
    val startState = State(Vector(Stack(1, 2, 3), Stack(), Stack()))
    val goal = State(Vector(Stack(), Stack(), Stack(1, 2, 3)))
  }

  object FourDisks extends Solver {
    val startState = State(Vector(Stack(1, 2, 3, 4), Stack(), Stack()))
    val goal = State(Vector(Stack(), Stack(), Stack(1, 2, 3, 4)))
  }

  object FiveDisks extends Solver {
    val startState = State(Vector(Stack(1, 2, 3, 4, 5), Stack(), Stack()))
    val goal = State(Vector(Stack(), Stack(), Stack(1, 2, 3, 4, 5)))
  }

  object SixDisks extends Solver {
    val startState = State(Vector(Stack(1, 2, 3, 4, 5, 6), Stack(), Stack()))
    val goal = State(Vector(Stack(), Stack(), Stack(1, 2, 3, 4, 5, 6)))
  }

  "A State" should {
    "correctly output its neighbors" in {
      State(Vector(Stack(1, 2, 3, 4, 5, 6, 7, 8), Stack(), Stack())).neighbors mustEqual Set(
        (State(Vector(Stack(2, 3, 4, 5, 6, 7, 8), Stack(1), Stack())), Move(0, 1)),
        (State(Vector(Stack(2, 3, 4, 5, 6, 7, 8), Stack(), Stack(1))), Move(0, 2)))

      State(Vector(Stack(4, 5, 6), Stack(1, 2, 3), Stack(7, 8, 9))).neighbors mustEqual Set(
        (State(Vector(Stack(5, 6), Stack(1, 2, 3), Stack(4, 7, 8, 9))), Move(0, 2)),
        (State(Vector(Stack(4, 5, 6), Stack(2, 3), Stack(1, 7, 8, 9))), Move(1, 2)),
        (State(Vector(Stack(1, 4, 5, 6), Stack(2, 3), Stack(7, 8, 9))), Move(1, 0)))
    }
  }

  "A Solver" should {
    "correctly identify the done state" in {
      ThreeDisks.done(ThreeDisks.goal) must beTrue
    }

    "correctly output neighbors with history" in {
      ThreeDisks.neighborsWithHistory(ThreeDisks.startState, Nil).toSet mustEqual Set(
        (State(Vector(Stack(2, 3), Stack(1), Stack())), List(Move(0, 1))),
        (State(Vector(Stack(2, 3), Stack(), Stack(1))), List(Move(0, 2))))
    }

    "correctly output new neighbors" in {
      ThreeDisks.newNeighborsOnly(
        ThreeDisks.neighborsWithHistory(ThreeDisks.startState, Nil),
        Set(State(Vector(Stack(2, 3), Stack(1), Stack())))).toSet mustEqual Set(
        (State(Vector(Stack(2, 3), Stack(), Stack(1))), List(Move(0, 2))))
    }

    "correctly identify possible paths" in {
      ThreeDisks.from(
        Set((ThreeDisks.startState, List())).toStream,
        Set(ThreeDisks.startState)).take(9).toSet mustEqual Set(
          (State(Vector(Stack(1, 2, 3), Stack(), Stack())),List()),
          (State(Vector(Stack(2, 3), Stack(1), Stack())),List(Move(0, 1))),
          (State(Vector(Stack(2, 3), Stack(), Stack(1))),List(Move(0, 2))),
          (State(Vector(Stack(3), Stack(1), Stack(2))),List(Move(0, 2), Move(0, 1))),
          (State(Vector(Stack(3), Stack(2), Stack(1))),List(Move(0, 1), Move(0, 2))),
          (State(Vector(Stack(1, 3), Stack(), Stack(2))),List(Move(1, 0), Move(0, 2), Move(0, 1))),
          (State(Vector(Stack(3), Stack(), Stack(1, 2))),List(Move(1, 2), Move(0, 2), Move(0, 1))),
          (State(Vector(Stack(1, 3), Stack(2), Stack())),List(Move(2, 0), Move(0, 1), Move(0, 2))),
          (State(Vector(Stack(3), Stack(1, 2), Stack())),List(Move(2, 1), Move(0, 1), Move(0, 2))))
    }

    "correctly identify the minimum number of moves" in {
      ThreeDisks.solution.size mustEqual (1 << 3) - 1
      FourDisks.solution.size mustEqual (1 << 4) - 1
      FiveDisks.solution.size mustEqual (1 << 5) - 1
      SixDisks.solution.size mustEqual (1 << 6) - 1
    }

    "correctly identify the optimal moves" in {
      ThreeDisks.solution mustEqual List(
        Move(0, 2),
        Move(0, 1),
        Move(2, 1),
        Move(0, 2),
        Move(1, 0),
        Move(1, 2),
        Move(0, 2))
    }
  }
}
