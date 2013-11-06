package weather

import com.github.nscala_time.time.Imports._

object Weather extends App {
  private[this] lazy val formatter = DateTimeFormat.forPattern("yyyyMMdd")

  def toLocalDate(s: String): LocalDate =
    formatter.parseDateTime(s).toLocalDate

  /// YOUR CODE SHOULD GO HERE ///
}
