# 05 Additional Algorithms

## 01 Pipelines

[Pipelines](01-pipelines/src/main.scala)

## 02 IO

[IO](02-io/src/main.scala)

## 03 LazyList and Streams

### Exercise 01 - S

```scala
Stream(1).repeat.take(3).toList // List(1, 1, 1)
Stream(1).append(Stream(0,1).repeat).take(4).toList // List(1, 0, 1, 0)
Stream(2).map(_ * 13).repeat.take(1).toList // List(26)
Stream(13).filter(_ % 2 != 0).repeat.take(2).toList // List(13, 13)
```

### Exercise 02 - Meaningful LazyLists

#### 1. Sequence of natural numbers

```scala
val naturals: LazyList[Int] = LazyList.from(1)
```

#### 2. Sequence of even numbers

```scala
val evens: LazyList[Int] = LazyList.from(1).map(_ * 2)
```

#### 3. Sequence of powers of 2

```scala
val powersOfTwo: LazyList[BigInt] = LazyList.iterate(BigInt(2))(_ * 2)
```

#### 4. Count letters in sequence

```scala
def letters: LazyList[String] = {
  def next(s: String): String = {
    s.reverse.foldLeft(("", true)) {
      case ((acc, carry), c) if carry && c == 'z' => ('a' + acc, true)
      case ((acc, true), c) => ((c + 1).toChar + acc, false)
      case ((acc, false), c) => (c + acc, false)
    } match {
      case (res, true) => "a" + res
      case (res, false) => res
    }
  }
  LazyList.iterate("a")(next)
}
```

#### 5. Random numbers from 1 to 6

```scala
import cats.effect.IO

def diceStream: LazyList[IO[Int]] = LazyList.continually(rollDice())

// Execution
diceStream.take(5).sequence.unsafeRunSync()
```

## 04 Parallel Processes

### Code 1

```scala
for {
  _ <- IO.sleep(1.second)
  result <- List(rollDice(), rollDice()).parSequence
} yield result.sum
```

#### What happens

- Wait for 1 second
- Roll two dice in parallel
- Sum the results

No shared state -> safe to run in parallel.

### Code 2

```scala
for {
  storedCasts <- Ref.of[IO, List[Int]](List.empty)
  singleCast = rollDice()
    .flatMap(result => storedCasts.update(_.appended(result)))
  _ <- List(singleCast, singleCast).parSequence
  casts <- storedCasts.get
} yield casts
```

#### What happens

- Shared storage (Ref)
- Two parallel computations updating the same Ref
- Every result is stored thread-safe
- Resulting list contains two values

### Code 3

```scala
List.fill(3)(singleCast).parSequence
```

#### What happens

Like Code 2, but:

- Three parallel computations updating the same Ref
- Resulting list contains three values

### Code 4

```scala
storedCasts <- Ref.of
singleCast =
  rollDice().flatMap {
    case 6 => storedCasts.update(_ + 1)
    case _ => IO.unit
  }
```

#### What happens

- 100 parallel computations updating the same Ref
- Only rolls of 6 are counted
- Result ≈ 16-17 (statistically expected)

Ref prevents race conditions

### Code 5

```scala
List.fill(100)(IO.sleep(1.second).flatMap(_ => rollDice()))
  .parSequence
  .map(_.sum)
```

#### What happens

- 100 Tasks sleeping for 1 second in parallel
- After 1 second, all tasks wake up and roll dice in parallel
- Sum the results

Total runtime ≈ 1 second (not 100 seconds) due to parallel execution.

## 05 Bonus

[Bonus](05-bonus/src/main.scala)
