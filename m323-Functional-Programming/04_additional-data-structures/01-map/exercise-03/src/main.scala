@main def run(): Unit = {
  val m1: Map[String, String] = Map("key" -> "value", "key2" -> "value2")
  val m2: Map[String, String] = m1 + ("key2" -> "aDifferentValue") // Replace the value for "key2"
  println(m2)
}