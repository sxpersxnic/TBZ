@main def run(): Unit = {
  val numbers = 1 to 10

  val squares = for {
    n <- numbers
  } yield n * n

  println(squares.toList)
}