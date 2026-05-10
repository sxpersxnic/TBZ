object ImperativeScore {
  def calculateScore(word: String): Int = {
    var score = 0
    for (c <- word) {
      if (c != 'a') score += 1
    }
    score
  }
}

object DeclarativeScore {
  def wordScore(word: String): Int = word.count(_ != 'a')
}

@main def run(): Unit = {
  println("=== Exercise 01 ===")
  println(ImperativeScore.calculateScore("imperative"))
  println(ImperativeScore.calculateScore("no"))
  println(DeclarativeScore.wordScore("declarative"))
  println(DeclarativeScore.wordScore("yes"))
}