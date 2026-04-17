@main def run(): Unit = {
  val colors = List("Red", "Green", "Blue")
  val fruits = List("Apple", "Banana", "Orange")

  val combinations = for {
    color <- colors
    fruit <- fruits
  } yield (color, fruit)

  combinations.foreach(println)
}