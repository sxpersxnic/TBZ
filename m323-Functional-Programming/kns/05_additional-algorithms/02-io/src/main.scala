//> using scala "3.3.1"
//> using dep "org.typelevel::cats-effect:3.6.3"

import cats.effect.{IO, IOApp}
import scala.util.Random

object Main extends IOApp.Simple {
  def rollDiceImpure(): Int = Random.nextInt(6) + 1
  def rollDice(): IO[Int] = IO.delay(rollDiceImpure())
  def allowToLeaveHome: IO[Boolean] = rollDice().map(_ == 6)

  override def run: IO[Unit] = {
    for {
      allowed <- allowToLeaveHome
      _ <- IO.println(s"Allowed to leave home: $allowed")
    } yield ()
  }
}
