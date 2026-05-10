@main def run(): Unit = {
  val list = List(1, 2, 3, 4, 5)
  val sum = list.foldLeft(0)(_ + _)
  println(s"Sum of the list: $sum")
}