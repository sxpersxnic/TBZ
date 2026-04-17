@main def run(): Unit = {
  val map: Map[String, String] = Map("key" -> "value", "key2" -> "value2")
  val valueFromMap: Option[String] = map.get("key")
  println(valueFromMap) // Output: Some(value)
}