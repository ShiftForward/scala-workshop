
/**
 * Case class that represents a line in the weather data file.
 */
case class WeatherEntry(station: Int, source: Int, date: SimpleDate, temperature: Double, quality: Int)

/**
 * A representation of a date in the dataset.
 */
case class SimpleDate(year: Int, month: Int, day: Int)