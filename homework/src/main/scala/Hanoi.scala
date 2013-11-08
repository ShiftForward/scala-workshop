import scala.collection.immutable.Stack

object Hanoi extends App {
  object EightDisks extends Solver {
    val startState = State(Vector(Stack(1, 2, 3, 4, 5, 6, 7, 8), Stack(), Stack()))
    val goal = State(Vector(Stack(), Stack(), Stack(1, 2, 3, 4, 5, 6, 7, 8)))
  }

  println(EightDisks.solution)
}
