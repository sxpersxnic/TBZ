case class Course(title: String, students: List[String])
case class CourseSubscriptions(title: String, totalStudents: Int)

def coursesOf(name: String, courses: List[Course]): String = {
  val modules =
    courses
      .filter(_.students.contains(name))
      .map(_.title)
      .mkString(", ")
  s"$name is enrolled in: $modules"
}

def countSubscriptions(courses: List[Course]): List[CourseSubscriptions] =
  courses.map(course => CourseSubscriptions(course.title, course.students.size))

@main def run(): Unit = {
  val courses = List(
    Course("M323", List("Alice", "Bob", "Charlie", "Joe")),
    Course("M183", List("Joe", "David", "Eve")),
    Course("M117", List("Charlie", "Joe")),
    Course("M114", List("Bob", "Charlie", "Joe"))
  )

  val coursesOfJoe = coursesOf("Joe", courses)
  val coursesOfAlice = coursesOf("Alice", courses)
  val subscriptions = countSubscriptions(courses)

  println(coursesOfJoe)
  println(coursesOfAlice)
  println(subscriptions)
}