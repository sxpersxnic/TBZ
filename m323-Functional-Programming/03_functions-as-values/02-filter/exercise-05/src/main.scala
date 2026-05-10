case class Book(title: String, author: String, year: Int)

def filterBooksByYear(books: List[Book], yearThreshold: Int): List[Book] = {
  books.filter(_.year > yearThreshold)
}

@main def run(): Unit = {
  val books = List(
    Book("The Great Gatsby", "F. Scott Fitzgerald", 1925),
    Book("To Kill a Mockingbird", "Harper Lee", 1960),
    Book("1984", "George Orwell", 1948)
  )
  val recentBooks = filterBooksByYear(books, 1950)
  println(recentBooks)
}