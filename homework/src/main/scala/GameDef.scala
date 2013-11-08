import scala.collection.immutable.Stack

/**
 * Case class to encode the game state, by representing the different stacks of
 * disks that compose the board.
 * @param stacks a vector of stacks of disk sizes, that composes the board
 */
case class State(stacks: Vector[Stack[Int]]) {
  /**
   * Returns the list of states that are reachable in the current board
   * state, together with the corresponding move.
   * @return the list of states that are reachable in the current board state,
   *         together with the corresponding moves.
   */
  def neighbors: Set[(State, Move)] = ???
}

/**
 * Case class to encode a possible move in the board. A move consists of
 * transfering the topmost disk from the stack indexed by `source` to the top of
 * the stack indexed by `target`.
 * @param source the stack to remove the disk from
 * @param target the stack to place the disk in
 */
case class Move(source: Int, target: Int)

/**
 * Trait representing the definitions of a Tower of Hanoi game.
 */
trait GameDef {
  /**
   * The initial state of the board.
   */
  val startState: State

  /**
   * The target state of the board.
   */
  val goal: State
}
