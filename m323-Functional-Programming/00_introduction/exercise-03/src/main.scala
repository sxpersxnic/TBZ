object TipCalculator {
  def calculateTipPercentage(names: List[String]): Int = {
    val size = names.size

    if (size == 0) 0
    else if (size <= 5) 10
    else 20
  }
}

@main def run(): Unit = {
  println("=== Exercise 03 ===")

  val groupSmall = List("Alice", "Bob")
  val groupLarge = List("Alice", "Bob", "Charlie", "David", "Eve", "Frank")
  val groupEmpty = List.empty[String]

  println(TipCalculator.calculateTipPercentage(groupSmall)) // 10
  println(TipCalculator.calculateTipPercentage(groupLarge)) // 20
  println(TipCalculator.calculateTipPercentage(groupEmpty)) // 0
}