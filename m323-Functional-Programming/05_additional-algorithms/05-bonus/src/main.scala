//> using scala "3.3.1"
//> using dep "org.typelevel::cats-effect:3.6.3"

import cats.effect.{IO, IOApp, Ref}
import cats.syntax.all._
import scala.concurrent.duration._
import scala.util.Random

object Main extends IOApp.Simple {
  final case class Inventory(stock: Int)

  def randomOrderAmount(): IO[Int] = IO.delay(Random.between(1, 3))

  def tryPurchase(inventory: Ref[IO, Inventory], amount: Int ): IO[Boolean] = {
    inventory.modify { inv =>
      if (inv.stock >= amount)
        (inv.copy(stock = inv.stock - amount), true)
      else
        (inv, false)
    }
  }

  def reorderProducts(inventory: Ref[IO, Inventory], amount: Int): IO[Unit] = inventory.update(inv => inv.copy(stock = inv.stock + amount))

  def customer(id: Int, inventory: Ref[IO, Inventory]): IO[Unit] = {
    for {
      amount <- randomOrderAmount()
      success <- tryPurchase(inventory, amount)
      _ <-
        if (success)
          IO.println(s"Customer $id bought $amount item(s)")
        else
          IO.println(s"Customer $id FAILED to buy $amount item(s)")
    } yield ()
  }

  override def run: IO[Unit] = {
    for {
      inventory <- Ref.of[IO, Inventory](Inventory(stock = 10))

      _ <- IO.println("Starting inventory with 10 items\n")

      _ <- List
        .range(1, 21)
        .map(id => customer(id, inventory))
        .parSequence

      remaining <- inventory.get
      _ <- IO.println(s"\nStock after purchases: ${remaining.stock}")

      _ <- IO.println("\nReordering 5 items...")
      _ <- reorderProducts(inventory, 5)

      finalStock <- inventory.get
      _ <- IO.println(s"Final stock: ${finalStock.stock}")
    } yield ()
  }
}