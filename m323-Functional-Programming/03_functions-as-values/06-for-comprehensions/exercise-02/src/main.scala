@main def run(): Unit = {
  val numbers = 1 to 20

  val evens = for {
    n <- numbers
    if n % 2 == 0
  } yield n

  println(evens.toList)
}