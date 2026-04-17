def filterList(searchStr: String, list: List[String]): List[String] = {
  list.filter(searchStr)
}

def removeWhitespaces(str: String): String = {
  str.replaceAll("\\s", "")
}

def sortListAlphabetically(list: List[String], direction: String): List[String] = {
  if (direction == "ascending") {
    list.sorted
  } else {
    list.reverse.sorted
  }
}

@main def run(): Unit = {
  val courses = List("Functional Programming", "Object-Oriented Programming", "Data Structures", "Algorithms")
  val programmingCourses = filterList("Programming", courses)
  println(s"Programming courses: $programmingCourses")
  val courseWithNoWhitespaces = courses.map(removeWhitespaces)
  println(s"Courses with no whitespaces: $courseWithNoWhitespaces")
  val sortedCourses = sortListAlphabetically(courseWithNoWhitespaces, "ascending")
  println(s"Sorted courses (ascending): $sortedCourses")
  val sortedCoursesDescending = sortListAlphabetically(courseWithNoWhitespaces, "descending")
  println(s"Sorted courses (descending): $sortedCoursesDescending")
}