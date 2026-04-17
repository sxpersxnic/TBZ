def getFirstName(fullName: String): String = {
  fullName.split(" ")(0)
}

def stringToUpperCase(s: String): String = s.toUpperCase

def firstNamesToUpperCase(names: List[String]): List[String] = {
  names.map(getFirstName).map(stringToUpperCase)
}

@main def run(): Unit = {
  val names = List("Alice Smith", "Bob Johnson", "Charlie Brown")
  val uppercasedFirstNames = firstNamesToUpperCase(names)
  println(uppercasedFirstNames)
}