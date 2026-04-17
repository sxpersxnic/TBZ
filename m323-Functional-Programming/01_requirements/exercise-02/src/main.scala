object WordScoring {

  def scoreWord(word: String): Int =
    word.count(_ != 'a')

  def scoreWords(words: List[String]): List[(String, Int)] =
    words.map(word => word -> scoreWord(word))

  def sortByScore(scoredWords: List[(String, Int)]): List[(String, Int)] =
    scoredWords.sortBy { case (_, score) => -score }
}

@main def run(): Unit = {
  println("=== Exercise 02 ===")

  val words = List("banana", "apple", "grape", "avocado")
  println(s"Original words: $words")
  val scoredWords = WordScoring.scoreWords(words)
  println(s"Scored words: $scoredWords")
  val sortedWords = WordScoring.sortByScore(scoredWords)
  println(s"Sorted words by score: $sortedWords")
}