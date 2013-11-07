trait Solver extends GameDef {
  /**
   * Returns `true` if the state `s` is at the final position.
   */
  def done(s: State): Boolean = ???

  /**
   * This function takes two arguments: the current state `s` and a list of
   * moves `history` that was required to reach the position of `s`.
   *
   * The `head` element of the `history` list is the latest move that was
   * executed, i.e. the last move that was performed for the block to end up at
   * position `b`.
   *
   * The function returns a stream of pairs: the first element of the each pair
   * is a neighboring state, and the second element is the augmented history of
   * moves required to reach this block.
   */
  def neighborsWithHistory(s: State, history: List[Move]): Stream[(State, List[Move])] = ???

  /**
   * This function returns the list of neighbors without the states that have
   * already been explored.
   */
  def newNighborsOnly(
    neighbors: Stream[(State, List[Move])],
    explored: Set[State]): Stream[(State, List[Move])] = ???

  /**
   * The function `from` returns the stream of all possible state paths that can
   * be followed, starting at the `head` of the `initial` stream.
   *
   * The states in the stream `initial` are sorted by ascending path lengths:
   * the state positions with the shortest number of moves that lead to them are
   * at the head of the stream.
   *
   * The parameter `explored` is a set of state positions that have been visited
   * before, on the path to any of the states in the stream `initial`. When
   * search reaches a state that has already been explored before, that position
   * should not be included a second time to avoid circles.
   *
   * The resulting stream should be sorted by ascending path length, i.e. the
   * states that can be reached with the fewest amount of moves should
   * appear first in the stream.
   *
   * Note: the solution should not look at or compare the lengths of different
   * paths - the implementation should naturally construct the correctly sorted
   * stream.
   */
  def from(
    initial: Stream[(State, List[Move])],
    explored: Set[State]): Stream[(State, List[Move])] = ???

  /**
   * The stream of all paths that begin at the starting state.
   */
  lazy val pathsFromStart: Stream[(State, List[Move])] =
    from(
      Set((startState, List())).toStream,
      Set(startState))

  /**
   * Returns a stream of all possible paths to the goal state along with the
   * history how it was reached.
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
