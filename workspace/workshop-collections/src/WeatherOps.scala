import scala.io.Source

/**
 * Class containing operations for processing a weather dataset.
 * 
 * Klein Tank, A.M.G. and Coauthors, 2002. Daily dataset of 20th-century surface
 * air temperature and precipitation series for the European Climate Assessment.
 *   Int. J. of Climatol., 22, 1441-1453.
 *   Data and metadata available at http://www.ecad.eu
 * 
 * FILE FORMAT (MISSING VALUE CODE = -9999):
 * 
 * 01-06 STAID: Station identifier
 * 08-13 SOUID: Source identifier
 * 15-22 DATE : Date YYYYMMDD
 * 24-28 TG   : Mean temperature in 0.1 ºC
 * 30-34 Q_TG : quality code for TG (0='valid'; 1='suspect'; 9='missing')
 * 
 * @param filename the filename containing the dataset
 */
case class WeatherOps(lines: Iterator[String]) {
  
  /**
   * Parses a line of the dataset.
   * @param the line to parse
   * @return a `WeatherEntry` instance representing the given line.
   */
  private def parseLine(str: String): WeatherEntry = ???
  
  /**
   * Returns an iterator of weather entries in this dataset.
   * @return an iterator of weather entries in this dataset.
   */
  lazy val entries: List[WeatherEntry] = ???
  
  /**
   * Returns the first weather entries of the dataset.
   * @param n the number of weather entries to return
   * @return the first `n` weather entries of the dataset.
   */
  def firstEntries(n: Int): List[WeatherEntry] = ???
  
  /**
   * Returns the set of stations in the dataset.
   * @return the set of stations in the dataset.
   */
  def stations: Set[Int] = ???
  
  /**
   * Returns the average temperature of the dataset. Note that some entries may not have a
   * temperature and must be ignored.
   * @return the average temperature of the dataset.
   */
  def avgTemperature: Double = ???
  
  /**
   * Returns the average temperature of a given station.
   * @param station the station to which the average temperature is to be computed
   * @return the average temperature of a given station.
   * @tip it may be useful to extract the logic of computing the temperature of an iterator of
   * 	  entries to an auxiliary function!
   */
  def avgTemperatureByStation(station: Int): Double = ???
  
  /**
   * Returns the identifier of the hottest station, according to its average temperature.
   * @return the identifier of the hottest station.
   */
  def hottestStation: Int = ???
  
    /**
   * Returns the identifier of the coldest station, according to its average temperature.
   * @return the identifier of the coldest station.
   */
  def coldestStation: Int = ???
  
  /**
   * Returns a summary for a station on a given year.
   * @param station the station to which the summary should be computed
   * @param year the year for which the summary should be computed
   * @return a map mapping each month of the given year to the average temperature on the station.
   */
  def yearSummary(station: Int, year: Int): Map[Int, Double] = ???
}

object WeatherOps {
  def apply(filename: String): WeatherOps =
    WeatherOps(Source.fromFile(filename).getLines)
}
