# 02 Pure Functions

## Exercise 01 - Pure vs. Impure

|Exercise|Returns a value|Depends only on parameters|No state change|Pure/Impure|
|--------|---------------|-------------------------|---------------|------------|
|1.1 - addToCart|Yes|No|No|Impure|
|1.2 - add|Yes|Yes|Yes|Pure|
|1.3 - firstCharacter|No|Yes|Yes|Impure|
|1.4 - multiplyWithRandom|Yes|No|Yes|Impure|
|1.5 - divideNumbers|No|Yes|Yes|Impure|
|1.6 - printAndReturnString|Yes|No|No|Impure|

## Exercise 02 - Refactor to Pure Functions

### 2.1 - addToCart

**Problem:** Shared mutable list

```scala
def addToCart(cart: List[String], item: String): List[String] = cart :+ item
```

### 2.2 - multiplyWithRandom

**Problem:** Randomness is inherently impure

```scala
def multiply(number: Double, randomValue: Double): Double = number * randomValue
```

### 2.3 - printAndReturnString

**Problem:** Original behavior cannot be preserved exactly because of side effects (printing). We can return the string and log separately.

```scala
// Pure alternative
def returnString(str: String): String = str

// Or, if logging must be modeled
def stringWithLog(str: String): (String, String) = {
  (str, s"LOG: $str")
}
```

## Exercise 03 - Writing own Pure Functions

### 3.1 - sum

```scala
def sum(numbers: List[Int]): Int = numbers match {
  case Nil => 0
  case head :: tail => head + sum(tail)
}
```

### 3.2 - average

```scala
def average(numbers: List[Double]): Double = {
  def helper(nums: List[Double], acc: Double, count: Int): Double = nums match {
    case Nil => if (count == 0) 0.0 else acc / count
    case h :: t => helper(t, acc + h, count + 1)
  }
  helper(numbers, 0.0, 0)
}
```

### 3.3 - sortStrings

```scala
def insert(x: String, sorted: List[String]): List[String] = sorted match {
  case Nil => List(x)
  case h :: t =>
    if (x <= h) x :: sorted
    else h :: insert(x, t)
}

def sortStrings(strings: List[String]): List[String] = strings match {
  case Nil => Nil
  case h :: t => insert(h, sortStrings(t))
}
```

### 3.4 - sortTasks

```scala
case class Task(date: String, priority: Int, title: String)

def insertTask(task: Task, sorted: List[Task]): List[Task] = sorted match {
  case Nil => List(task)
  case h :: t =>
    if (task.date, task.priority, task.title) < (h.date, h.priority, h.title) task :: sorted
    else h :: insertTask(task, t)
}

def sortTasks(tasks: List[Task]): List[Task] = tasks match {
  case Nil => Nil
  case h :: t => insertTask(h, sortTasks(t))
}
```

### 3.5 - leaves

```scala
case class Node(value: String, children: List[Node])

def leaves(node: Node): List[String] =
  if (node.children.isEmpty)
    List(node.value)
  else
    node.children.flatMap(leaves)
```
