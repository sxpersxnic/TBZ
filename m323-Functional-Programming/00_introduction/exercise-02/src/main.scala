object ShoppingCartFunctional {
  def getDiscountPercentage(cart: List[String]): Int = if (cart.exists(_.toLowerCase.contains("book"))) 5 else 0
}

@main def run(): Unit = {
  println("=== Exercise 02 ===")

  val cart1 = List("pen", "scala book")
  val cart2 = List("apple", "banana")

  println(ShoppingCartFunctional.getDiscountPercentage(cart1)) // 5
  println(ShoppingCartFunctional.getDiscountPercentage(cart2)) // 0
}