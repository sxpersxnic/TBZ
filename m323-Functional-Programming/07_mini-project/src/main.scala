type Column = Int
type Row    = Int
type Board  = List[Column]

object EightQueens {

  def isSafe(column: Column, board: Board): Boolean =
    board.zipWithIndex.forall {
      case (existingColumn, rowDistance) =>
        existingColumn != column &&
        math.abs(existingColumn - column) != rowDistance + 1
    }

  def placeQueens(size: Int, board: Board = Nil): List[Board] =
    board.length match {
      case `size` => List(board)
      case _ =>
        (0 until size).toList
          .filter(col => isSafe(col, board))
          .flatMap(col => placeQueens(size, col :: board))
    }

  def placeQueensFor(size: Int, board: Board = Nil): List[Board] =
    board.length match {
      case `size` => List(board)
      case _ =>
        for {
          col <- (0 until size).toList
          if isSafe(col, board)
          solution <- placeQueensFor(size, col :: board)
        } yield solution
    }

  def render(board: Board, size: Int): String =
    board.reverse
      .map { col =>
        (0 until size)
          .foldLeft(List.empty[String]) {
            case (acc, c) => acc :+ (if (c == col) "Q" else ".")
          }
          .mkString(" ")
      }
      .mkString("\n")

  def statistics(solutions: List[Board]): Option[String] =
    solutions match {
      case Nil => None
      case list =>
        Some(
          s"""
             || Statistics:
             || - Number of solutions: ${list.length}
             || - Board size: ${list.head.length}x${list.head.length}
           """.stripMargin.trim
        )
    }
}

@main def run(): Unit = {
  import EightQueens._

  val size: Int = 8
  val solutions: List[Board] = placeQueens(size)

  println("=== Eight Queens Problem ===\n")

  statistics(solutions).foreach(println)

  solutions.headOption match {
    case Some(board) =>
      println("\nExample solution:\n")
      println(render(board, size))
    case None =>
      println("No solution found.")
  }
}