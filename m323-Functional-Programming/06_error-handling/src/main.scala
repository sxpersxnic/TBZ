case class TvShow(title: String, start: Int, end: Int)

def extractName(show: String): Either[String, String] = {
  val bracketOpen = show.indexOf('(')

  if (bracketOpen > 0)
    Right(show.substring(0, bracketOpen).trim)
  else
    Left(s"Can't extract name from: $show")
}

def extractYearEnd(rawShow: String): Option[Int] = {
  val bracketOpen = rawShow.indexOf('(')
  val bracketClose = rawShow.indexOf(')')

  if (bracketOpen > 0 && bracketClose > bracketOpen) {
    val years = rawShow.substring(bracketOpen + 1, bracketClose).split("-").map(_.trim)

    if (years.length == 2) {
      val yearEndStr = years(1)

      if (yearEndStr.nonEmpty)
        Some(yearEndStr.toInt)
      else
        None
    } else
      None
  } else
    None
}

def extractYearEndEither(rawShow: String): Either[String, Int] = {
  extractYearEnd(rawShow) match {
    case Some(year) => Right(year)
    case None       => Left(s"Can't extract end year from: $rawShow")
  }
}
def extractYearStart(rawShow: String): Option[Int] = {
  val bracketOpen = rawShow.indexOf('(')
  val bracketClose = rawShow.indexOf(')')

  if (bracketOpen > 0 && bracketClose > bracketOpen) {
    val years = rawShow.substring(bracketOpen + 1, bracketClose).split("-").map(_.trim)

    if (years.length == 2) {
      val yearStartStr = years(0)

      if (yearStartStr.nonEmpty)
        Some(yearStartStr.toInt)
      else
        None
    } else
      None
  } else
    None
}

def extractYearStartEither(rawShow: String): Either[String, Int] = {
  extractYearStart(rawShow) match {
    case Some(year) => Right(year)
    case None       => Left(s"Can't extract start year from: $rawShow")
  }
}

def parseShow(rawShow: String): Either[String, TvShow] =
  for {
    name      <- extractName(rawShow)
    yearStart <- extractYearStartEither(rawShow)
    yearEnd   <- extractYearEndEither(rawShow)
  } yield TvShow(name, yearStart, yearEnd)

@main def run(): Unit = {
  val shows = List(
    "The Wire (2002-2008)",
    "Breaking Bad (2008-2013)",
    "Game of Thrones (2011-2019)",
    "The Sopranos (1999-2007)",
    "The Office (2005-2013)",
  )

  shows.map(parseShow).foreach {
    case Right(show) => println(s"Parsed show: $show")
    case Left(error) => println(s"Error: $error")
  }
}