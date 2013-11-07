import scala.collection.immutable.Stack

/**
 * This trait represents the definitions of a Tower of Hanoi game
 */
trait GameDef {
  type DiskSize = Int

  /**
   * The case class `State` encodes the game state, by representing the three
   * different stacks of disk sizes that compose the board.
   */
  case class State(stacks: Vector[Stack[DiskSize]]) {
    /**
     * Returns the list of states that are achievable by the current board
     * state, together with the corresponding move.
     */
    def neighbors: List[(State, Move)] = ???
  }

  /**
   * The case class `Move` encodes a possible move in the Tower of Hanoi game. A
   * move consists of transfering a disk from the stack referenced by the
   * `source` index to the stack referenced by the `target` index.
   */
  case class Move(source: Int, target: Int)

  /**
   * The initial state of the board.
   */
  val startState: State

  /**
   * The target state of the board.
   */
  val goal: State
}
