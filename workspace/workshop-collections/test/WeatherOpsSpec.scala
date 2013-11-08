import org.junit.runner.RunWith
import org.specs2.mutable._
import org.specs2.runner.JUnitRunner

@RunWith(classOf[JUnitRunner])
class WeatherAppSpec extends Specification {
  
  val weather1 = WeatherOps("resources/weather-test1.csv")
  val weather2 = WeatherOps("resources/weather-test2.csv")
  val weather3 = WeatherOps("resources/weather-test3.csv")

  "entries" should {
    
    "have all the dataset lines" in {
      weather1.entries.length mustEqual 60120
      weather2.entries.length mustEqual 56184
      weather3.entries.length mustEqual 57904
    }
    
    "correctly parse dataset lines to weather entries" in {
      weather1.entries.head mustEqual WeatherEntry(1, 8906, SimpleDate(1918, 1, 5), -4.6, 0)
      weather1.entries(1) mustEqual WeatherEntry(1, 8906, SimpleDate(1918, 2, 1), -0.2, 0)
    }
  }
  
  "firstEntries" should {
    "return the correct number of entries" in {
      weather1.firstEntries(0).length mustEqual 0
      weather2.firstEntries(1).length mustEqual 1
      weather3.firstEntries(2).length mustEqual 2
    }
    
    "return the correct entries" in {
      weather1.firstEntries(2) mustEqual List(
          WeatherEntry(1, 8906, SimpleDate(1918, 1, 5), -4.6, 0),
          WeatherEntry(1, 8906, SimpleDate(1918, 2, 1), -0.2, 0))
      
      weather2.firstEntries(1) mustEqual List(
          WeatherEntry(1, 8906, SimpleDate(1918, 6, 15), 11.7, 0))
      
      weather3.firstEntries(0) mustEqual Nil
    }
  }
  
  "stations" should {
    "return all the station identifiers" in {
      weather1.stations mustEqual (1 to 54).toSet -- (17 to 20).toSet
      weather2.stations mustEqual (1 to 54).toSet -- (17 to 20).toSet -- Set(15, 49)
      weather3.stations mustEqual (1 to 54).toSet -- (17 to 20).toSet -- Set(7, 23, 34)
    }
  }
  
  "avgTemperature" should {
    "return the correct value" in {
      weather1.avgTemperature must beCloseTo(8.49018, 0.00001)
      weather2.avgTemperature must beCloseTo(8.83749, 0.00001)
      weather3.avgTemperature must beCloseTo(8.30702, 0.00001)
    }
  }
  
  "avgTemperatureByStation" should {
    "return the correct value" in {
      weather1.avgTemperatureByStation(12) must beCloseTo(9.62545, 0.00001)
      weather1.avgTemperatureByStation(34) must beCloseTo(13.09351, 0.00001)
      weather1.avgTemperatureByStation(5) must beCloseTo(6.33427, 0.00001)
      
      weather2.avgTemperatureByStation(2) must beCloseTo(4.51189, 0.00001)
      weather2.avgTemperatureByStation(46) must beCloseTo(7.94102, 0.00001)
      
      weather3.avgTemperatureByStation(11) must beCloseTo(8.17658, 0.00001)
      weather3.avgTemperatureByStation(53) must beCloseTo(9.67077, 0.00001)
    }
  }
  
  "hottestStation" should {
    "return the correct value" in {
      weather1.hottestStation mustEqual 23
      weather2.hottestStation mustEqual 23
      weather3.hottestStation mustEqual 24
    }
  }
  
  "coldestStation" should {
    "return the correct value" in {
      weather1.coldestStation mustEqual 15
      weather2.coldestStation mustEqual 30
      weather3.coldestStation mustEqual 15
    }
  }
  
  "yearSummary" should {
    "return the correct values" in {
      weather1.yearSummary(23, 1950) mustEqual Map()
      weather1.yearSummary(12, 1950) mustEqual Map(5 -> 18.5, 11 -> 3.5, 12 -> -1.1)
      weather1.yearSummary(23, 2100) mustEqual Map()
      weather1.yearSummary(-1, 1900) mustEqual Map()
      
      weather2.yearSummary(1, 1999) mustEqual Map(
          1 -> -0.13333333333333344,
          2 -> -4.3,
          5 -> 7.2,
          6 -> 13.825000000000001,
          7 -> 15.65,
          8 -> 16.35,
          11 -> 6.2,
          12 -> -2.0500000000000003)

      weather2.yearSummary(11, 1990) mustEqual Map(
          1 -> -6.6,
          2 -> 3.75,
          3 -> 6.7,
          5 -> 14.55,
          7 -> 16.450000000000003,
          9 -> 12.966666666666667,
          11 -> 5.65,
          12 -> -0.4)
      
      weather3.yearSummary(5, 1969) mustEqual Map(
          1 -> -7.4,
          5 -> 10.8,
          6 -> 17.7,
          8 -> 16.950000000000003,
          9 -> 14.5,
          10 -> 5.1,
          12 -> -2.6)

      weather3.yearSummary(15, 1940) mustEqual Map(
          2 -> -10.9,
          3 -> -8.55,
          4 -> -6.3,
          8 -> -5.0,
          9 -> -2.4,
          10 -> -6.3999999999999995,
          11 -> -6.8,
          12 -> -16.0)
    }
  }
}
