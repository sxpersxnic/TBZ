def half(x: Int): Int = x / 2

def listToHalfs(list: List[Int]): List[Int] = list.map(half)

@main def run(): Unit = {
  val list = List(12, 45, 68, 100)
  val halfs = listToHalfs(list)
  println(halfs)
}