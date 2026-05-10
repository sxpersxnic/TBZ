def toUpperCase(s: String): String = s.toUpperCase

def listToUpperCase(list: List[String]): List[String] = list.map(toUpperCase)

@main def run(): Unit = {
  val list = List("Alice", "Bob", "Charlie")
  val uppercased = listToUpperCase(list)
  println(uppercased)
}