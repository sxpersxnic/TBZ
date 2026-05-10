def double(x: Int): Int = x * 2

@main def run(): Unit = {
  val list = List(1, 2, 3, 4, 5)
  val doubled = list.map(double)
  println(doubled)
}