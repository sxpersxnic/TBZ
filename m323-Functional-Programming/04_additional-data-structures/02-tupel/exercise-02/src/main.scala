object WeatherAnalysis {
  type WeatherData = (String, String, Double)
  def citiesWarmerThan(data: List[WeatherData], minTemperature: Double): List[String] = data.collect {
    case (city, _, temp) if temp > minTemperature => city
  }
}

@main def run(): Unit = {
  val weatherData = List(
    ("New York", "Sunny", 25.0),
    ("Los Angeles", "Cloudy", 22.0),
    ("Chicago", "Rainy", 18.0),
    ("Miami", "Sunny", 30.0)
  )
  val warmCities = WeatherAnalysis.citiesWarmerThan(weatherData, 20.0)
  println(warmCities)
}