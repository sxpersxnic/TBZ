@main def run(): Unit = {
  val m1: Map[String, String] = Map("key" -> "value", "key2" -> "value2")
  val m2: Map[String, String] = m1 - "key" // Remove the key "key" and its associated value
  println(m2)
}