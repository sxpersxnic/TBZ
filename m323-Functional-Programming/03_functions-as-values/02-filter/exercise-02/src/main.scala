def filterForNamesWithMiniumLength(names: List[String], minLength: Int): List[String] = {
  names.filter(_.length >= minLength)
}

@main def run(): Unit = {
  val names = List("Alice", "Bob", "Charlie", "Diana")
  val filteredNames = filterForNamesWithMiniumLength(names, 4)
  println(filteredNames)
}