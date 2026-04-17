def filterForEvenNumbers(list: List[Int]): List[Int] = {
  list.filter(_ % 2 == 0)
}

@main def run(): Unit = {
  val list = List(1, 2, 3, 4, 5)
  val evenNumbers = filterForEvenNumbers(list)
  println(evenNumbers)
}