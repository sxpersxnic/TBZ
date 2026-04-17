import java.time.LocalTime

object WeatherService {
  def weather(): (String, LocalTime, Double) = ("Sunny", LocalTime.now(), 23.5)
}

@main def run(): Unit = {
  val (description, time, temperature) = WeatherService.weather()
  println(s"Today's weather is $description at $time with a temperature of $temperature °C")
}