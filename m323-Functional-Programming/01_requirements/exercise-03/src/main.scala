object RaceAnalytics {

  def validLaps(laps: List[Double]): List[Double] =
    laps.drop(1)

  def totalRaceTime(laps: List[Double]): Double =
    validLaps(laps).sum

  def averageLapTime(laps: List[Double]): Double = {
    val v = validLaps(laps)
    if (v.isEmpty) 0.0 else v.sum / v.size
  }
}

@main def run(): Unit = {
  println("=== Exercise 03 ===")
  val laps = List(120.5, 118.3, 122.1, 119.7)
  println(s"Original laps: $laps")
  val validLaps = RaceAnalytics.validLaps(laps)
  println(s"Valid laps: $validLaps")
  val totalRaceTime = RaceAnalytics.totalRaceTime(laps)
  println(s"Total race time: $totalRaceTime")
  val averageLapTime = RaceAnalytics.averageLapTime(laps)
  println(s"Average lap time: $averageLapTime")
}