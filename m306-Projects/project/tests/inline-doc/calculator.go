package main

import (
	"errors"
	"fmt"
)

// Calculator represents a simple calculator that tracks operation history.
// It maintains a history of all performed operations and can optionally use a Logger.
type Calculator struct {
	History []string
	Logger  *Logger
}

// NewCalculator creates and returns a new Calculator instance with an empty history.
func NewCalculator() *Calculator {
	return &Calculator{
		History: make([]string, 0),
	}
}

// Add performs addition of two float64 numbers and records the operation in history.
// It returns the sum of a and b.
func (c *Calculator) Add(a, b float64) float64 {
	result := a + b
	c.recordOperation(fmt.Sprintf("%.2f + %.2f = %.2f", a, b, result))
	return result
}

// Subtract performs subtraction of two float64 numbers and records the operation in history.
// It returns the difference of a and b (a - b).
func (c *Calculator) Subtract(a, b float64) float64 {
	result := a - b
	c.recordOperation(fmt.Sprintf("%.2f - %.2f = %.2f", a, b, result))
	return result
}

// Multiply performs multiplication of two float64 numbers and records the operation in history.
// It returns the product of a and b.
func (c *Calculator) Multiply(a, b float64) float64 {
	result := a * b
	c.recordOperation(fmt.Sprintf("%.2f * %.2f = %.2f", a, b, result))
	return result
}

// Divide performs division of two float64 numbers and records the operation in history.
// It returns the quotient of a and b (a / b) and an error if b is zero.
// Division by zero returns an error and does not record the operation.
func (c *Calculator) Divide(a, b float64) (float64, error) {
	if b == 0 {
		return 0, errors.New("division by zero is not allowed")
	}
	result := a / b
	c.recordOperation(fmt.Sprintf("%.2f / %.2f = %.2f", a, b, result))
	return result, nil
}

// recordOperation adds an operation string to the calculator's history.
func (c *Calculator) recordOperation(operation string) {
	c.History = append(c.History, operation)
}

// ShowHistory displays all recorded operations in the calculator's history.
// If no operations have been performed, it logs an info message using the Logger.
func (c *Calculator) ShowHistory() {
	if len(c.History) == 0 {
		c.Logger.Info("No operations performed yet")
		return
	}
	println("\n=== Calculator History ===")
	for i, op := range c.History {
		fmt.Printf("%d: %s\n", i+1, op)
	}
	println("=========================")
}

// ClearHistory removes all operations from the calculator's history and logs an info message.
func (c *Calculator) ClearHistory() {
	c.History = make([]string, 0)
	c.Logger.Info("History cleared")
}
