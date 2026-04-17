def averagePoint(points: List[(Int, Int)]): (Double, Double) = {
  val (sumX, sumY) = points.foldLeft((0.0, 0.0)) { case ((accX, accY), (x, y)) =>
    (accX + x, accY + y)
  }
  val count = points.length.toDouble
  (sumX / count, sumY / count)
}

@main def run(): Unit = {
  val points = List((1, 3), (2, 5), (4, 8), (6, 2))
  val average = averagePoint(points)
  println(s"Average point: $average")
}