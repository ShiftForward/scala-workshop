import scala.io.Source
import scala.util.Random
import java.io.PrintStream

object Weather extends App {
  
  val filename = "resources/weather.csv"

  /* `Source.fromFile(filename).getLines` returns an object of type `Iterator[String]` that
   * contains each line of the specified file. You can think of a Scala `Iterator` as just another
   * Scala collection (one in which operations are applied in a lazy way). As such, you can
   * directly use transformations such as `map`, `take` and `filter` directly on it.
   * 
   * However, note that an iterator can only be traversed once. If you need to traverse it multiple
   * times, you can convert it to a `List` by calling its `toList` method.
   */
  val lines = Source.fromFile(filename).getLines
  
  println("First 5 lines of " + filename + ":")
  lines.take(5).foreach(println)
}
