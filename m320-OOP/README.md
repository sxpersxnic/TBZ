# M320 – Objektorientiert programmieren

> **Object-oriented programming** · Year 2 (2024/25) · Autumn semester (HS) · Done

Object-oriented design and programming in Java: classes, interfaces, UML modelling and design patterns. Exercises are grouped by competence area (D, M, Q, V).

## Contents

| Section | Exercises | Description |
| --- | --- | --- |
| [Project](./0-Project/) | [API](./0-Project/app/api/) | Java / Gradle REST API with design patterns and diagrams |
| [D](./D/) | [D1](./D/D1/), [D2](./D/D2/), [D3](./D/D3/) | ATM / bank model, server application (Gradle) |
| [M](./M/) | [M1](./M/M1/), [M2](./M/M2/), [M3](./M/M3/) | UML: class diagram, sequence diagram, interfaces in class diagrams |
| [Q](./Q/) | [Q1](./Q/Q1/), [Q3](./Q/Q3/) | Quiz app (C++), breadth-first search |
| [V](./V/) | [V1](./V/V1-C-Interfaces/), [V2](./V/V2-B-UML-Implementieren/), [V3](./V/V3/) | Interfaces (stack), implementing UML, applying interfaces |

## Key concepts

### Programming paradigms

- Object Oriented
- Functional
- Procedural

### The four principles of OOP

#### Encapsulation

Encapsulation is the concept of bundling the data (attributes) and methods (functions) that operate on the data into a single unit called an object. This principle helps to hide the internal state of the object from the outside world and only exposes a controlled interface. By doing so, it promotes modularity and reduces complexity, making the code easier to maintain and understand.

#### Inheritance

Inheritance is a mechanism where a new class (child or subclass) inherits properties and behaviors (methods) from an existing class (parent or superclass). This promotes code reusability and establishes a natural hierarchy between classes. It allows you to create a new class based on an existing class, extending or modifying its behavior. For example, a `Vehicle` class can be a parent class for `Car` and `Bike` classes, which inherit common properties like `speed` and `fuel`.

#### Abstraction

Abstraction involves simplifying complex systems by modeling classes appropriate to the problem and working at the most relevant level of inheritance for a particular aspect of the problem. It allows you to focus on the essential qualities of an object rather than its specific details, making it easier to manage complexity. For instance, an `Animal` class can represent the concept of an animal without specifying whether it's a dog, cat, or bird.

#### Polymorphism

Polymorphism allows objects of different classes to be treated as objects of a common superclass. It enables a single interface to represent different underlying forms (data types). The most common use of polymorphism is when a parent class reference is used to refer to a child class object. This allows for methods to be used interchangeably, promoting flexibility and integration. For example, a `Shape` class can have a method `draw()`, and subclasses like `Circle` and `Square` can implement this method differently.

## Learning goals (LB)

- Konstruktoren
- Klassenvariablen
- Primitive und Komplexe Datentypen
- Rückgabewerte
- Kontrollstrukturen (Loops, Conditionals)
- Methoden
- Deklararieren / Initialisieren
- Statische Methoden
- Scopes
- Information Hiding

## Resources

- [Class Miro board](https://miro.com/app/board/uXjVLE30rto=/)

---

[← Back to overview](../README.md)
