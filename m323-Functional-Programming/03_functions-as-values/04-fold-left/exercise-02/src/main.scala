@main def run(): Unit = {
  val words = List("Hello", " ", "World", "!")
  val sentence = words.foldLeft("")(_ + _)
  println(s"Sentence: $sentence")
}