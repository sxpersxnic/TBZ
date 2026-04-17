@main def run(): Unit = {
  val m1: Map[String, String] = Map("key" -> "value")
  val m2: Map[String, String] = m1 + ("key2" -> "value2")
  println(m2)
}