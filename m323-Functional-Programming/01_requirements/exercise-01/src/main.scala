object TravelPlanner {

  def addDestination(route: List[String], destination: String): List[String] =
    route :+ destination

  def changeDestination(
    route: List[String],
    oldDestination: String,
    newDestination: String
  ): List[String] =
    route.map {
      case d if d == oldDestination => newDestination
      case d                        => d
    }
}

@main def run(): Unit = {
  println("=== Exercise 01 ===")

  val route = List("Paris", "Berlin", "Rome")
  println(s"Original route: $route")
  val updatedRoute = TravelPlanner.addDestination(route, "Madrid")
  println(s"Route after adding destination: $updatedRoute")
  val changedRoute = TravelPlanner.changeDestination(updatedRoute, "Berlin", "Vienna")
  println(s"Route after changing destination: $changedRoute")
}