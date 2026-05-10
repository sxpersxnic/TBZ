def filterByStartingCharacter(words: List[String], char: Char): List[String] = {
  words.filter(_.startsWith(char.toString))
}

@main def run(): Unit = {
  val words = List("Scala", "is", "fantastic", "for", "functional", "programming")
  val filteredWords = filterByStartingCharacter(words, 'S')
  println(filteredWords)
}