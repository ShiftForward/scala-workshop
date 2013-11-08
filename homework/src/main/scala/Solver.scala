/**
 * Trait exposing methods to solve the Tower of Hanoi game.
 */
trait Solver extends GameDef {
  /**
   * Returns `true` if the state `s` is at the final position.
   */
  def done(s: State): Boolean = ???

  /**
   * Returns a stream of pairs composed of a neighboring state, together with
   * the augmented history of moves required to reach each state.
   * @param s the state to return neighbors of
   * @param history the history of moves required to reach state `s`. The `Move`
   *                at the head` of the list is the most recent move
   * @return a stream of pairs composed of a neighboring state, together with
   *         the augmented history of moves required to reach each state.
   */
  def neighborsWithHistory(s: State, history: List[Move]): Stream[(State, List[Move])] = ???

  /**
   * Returns the stream of neighboring states without the states that have
   * already been explored.
   * @param neighbors list of candidate neighbors, together with their history
   *        of moves
   * @param explored set of already explored neighbors
   * @return the stream of neighboring states without the states that have
   *         already been explored.
   */
  def newNeighborsOnly(neighbors: Stream[(State, List[Move])], explored: Set[State]): Stream[(State, List[Move])] = ???

  /**
   * Returns the stream of all possible state paths that can be followed,
   * starting at the `head` of the `initial` stream.
   *
   * The states in the stream `initial` are sorted by ascending path lengths:
   * the state positions with the shortest number of moves that lead to them are
   * at the head of the stream.
   *
   * The parameter `explored` is a set of state positions that have been visited
   * before, on the path to any of the states in the stream `initial`. When
   * search reaches a state that has already been explored before, that position
   * should not be included a second time.
   *
   * The resulting stream should be sorted by ascending path length, i.e. the
   * states that can be reached with the fewest amount of moves should
   * appear first in the stream.
   *
   * @param initial the states to explore from
   * @param explored a set of already explored states
   * @return the stream of all possible state paths that can be followed,
   *         starting at the `head` of the `initial` stream.
   */
  def from(initial: Stream[(State, List[Move])], explored: Set[State]): Stream[(State, List[Move])] = ???

  /**
   * The stream of all paths that begin at the starting state.
   */
  lazy val pathsFromStart: Stream[(State, List[Move])] =
    from(
      Set((startState, List())).toStream,
      Set(startState))

  /**
   * The stream of all possible paths to the goal state along with the history
   * of how it was reached.
   */
  lazy val pathsToGoal: Stream[(State, List[Move])] =
    for {
      path <- pathsFromStart
      if (done(path._1))
    } yield path

  /**
   * The (or one of the) shortest sequence(s) of moves to reach the goal. If the
   * goal cannot be reached, the empty list is returned.
   *
   * Note: the `head` element of the returned list should represent the first
   * move that the player should perform from the starting position.
   */
  lazy val solution: List[Move] =
    if (pathsToGoal.isEmpty)
      Nil
    else
      pathsToGoal.head._2.reverse
}
