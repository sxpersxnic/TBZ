
@main def run(): Unit = {
  val list = List(List(1, 2), List(3, 4), List(5, 6))
  val distinctFlatListWithDoubledValues = list.flatMap(innerList => innerList.map(_ * 2)).distinct
  println(s"Distinct flat list with doubled values: $distinctFlatListWithDoubledValues")
}