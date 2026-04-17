@main def run(): Unit = {
  val list = List(("Alice", List("Blue", "Green")), ("Bob", List("Red", "Yellow")), ("Charlie", List("Green", "Purple")))
  val distinctColors = list.flatMap(_._2).distinct
  println(s"Distinct colors: $distinctColors")
}