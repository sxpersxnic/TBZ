# Inline Documentation Demo - Calculator & Logger Application

A simple Go application that demonstrates best practices for inline documentation. This project showcases a calculator with operation history tracking and a configurable logging system.

## Overview

This application provides:

-   **Calculator**: A calculator that performs basic arithmetic operations (addition, subtraction, multiplication, division) and maintains a history of all operations
-   **Logger**: A configurable logging utility with support for different log levels (INFO, WARN, ERROR)

The project emphasizes comprehensive inline documentation using Go's standard documentation conventions, making the code easy to understand and maintain.

## Features

### Calculator

-   ✅ Basic arithmetic operations: Add, Subtract, Multiply, Divide
-   ✅ Operation history tracking
-   ✅ Error handling for invalid operations (e.g., division by zero)
-   ✅ History display and clearing functionality

### Logger

-   ✅ Configurable log levels (INFO, WARN, ERROR)
-   ✅ Level-based message filtering
-   ✅ Fatal error handling with panic
-   ✅ Validation of log level configuration

## Project Structure

```text
inline-doc/
├── main.go         # Entry point demonstrating calculator and logger usage
├── calculator.go   # Calculator implementation with operation history
├── logger.go       # Logger implementation with configurable levels
├── go.mod          # Go module definition
└── README.md       # This file
```

## Getting Started

### Prerequisites

-   Go 1.25.1 or higher installed on your system
-   Basic understanding of Go programming

### Installation

1. **Navigate to the project directory:**

    ```bash
    cd /workspaces/TBZ/m306-Projects/project/tests/inline-doc
    ```

2. **Verify Go installation:**

    ```bash
    go version
    ```

3. **Download dependencies (if any):**

    ```bash
    go mod download
    ```

### Running the Application

**Run the application directly:**

```bash
go run .
```

**Or build and execute:**

```bash
go build -o calculator-app
./calculator-app
```

### Expected Output

When you run the application, you should see output similar to:

```text
[INFO] Application started
[INFO] Calculator initialized
[INFO] Addition result: 15.00
[INFO] Subtraction result: 12.00
[INFO] Multiplication result: 42.00
[INFO] Division result: 25.00
[WARN] Expected error: division by zero is not allowed

=== Calculator History ===
1: 10.00 + 5.00 = 15.00
2: 20.00 - 8.00 = 12.00
3: 6.00 * 7.00 = 42.00
4: 100.00 / 4.00 = 25.00
=========================
[INFO] Application finished
```

## Usage Examples

### Creating a Logger

```go
// Create a logger with INFO level
logger, err := NewLogger("INFO")
if err != nil {
    panic(err)
}

logger.Info("This is an info message")
logger.Warn("This is a warning")
logger.Error("This is an error")
```

### Using the Calculator

```go
// Create a new calculator instance
calc := NewCalculator()

// Perform operations
sum := calc.Add(10, 5)              // Returns 15.00
diff := calc.Subtract(20, 8)        // Returns 12.00
product := calc.Multiply(6, 7)      // Returns 42.00

// Division with error handling
result, err := calc.Divide(100, 4)
if err != nil {
    // Handle division by zero error
    fmt.Println("Error:", err)
} else {
    fmt.Println("Result:", result)  // Returns 25.00
}

// Display operation history
calc.ShowHistory()

// Clear history
calc.ClearHistory()
```

## Documentation Standards

This project follows Go's documentation conventions:

-   **Package-level documentation**: Describes the overall purpose of the package
-   **Type documentation**: Explains what each struct represents
-   **Function/Method documentation**: Details what each function does, its parameters, and return values
-   **Inline comments**: Clarifies complex logic within function bodies

All exported types, functions, and methods include comprehensive documentation comments starting with the name of the element being documented.

## Code Quality

-   ✅ **Comprehensive inline documentation** (80%+ coverage)
-   ✅ **Error handling** for edge cases
-   ✅ **Type safety** with proper Go types
-   ✅ **Clean code** following Go best practices

## Learning Objectives

This project demonstrates:

1. How to write effective inline documentation in Go
2. Proper error handling patterns
3. Struct-based design with methods
4. Package organization and module management
5. Logging patterns with configurable levels

## Module Information

**Module**: `github.com/sxpersxnic/TBZ/m306-Projects/project/tests/inline-doc`  
**Go Version**: 1.25.1

## License

This project is part of the TBZ M306 Projects coursework.

## Contributing

This is an educational project. For improvements or suggestions, please follow the standard Git workflow:

1. Create a feature branch
2. Make your changes with proper documentation
3. Submit a pull request

---

Part of M306 Projects - Testing & Documentation

