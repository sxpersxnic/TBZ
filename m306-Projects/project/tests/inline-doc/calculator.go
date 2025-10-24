package main

import (
	"errors"
	"fmt"
)

type Calculator struct {
	History []string
	Logger *Logger
}

func NewCalculator() *Calculator {
	return &Calculator{
		History: make([]string, 0),
	}
}

func (c *Calculator) Add(a, b float64) float64 {
	result := a + b
	c.recordOperation(fmt.Sprintf("%.2f + %.2f = %.2f", a, b, result))
	return result
}

func (c *Calculator) Subtract(a, b float64) float64 {
	result := a - b
	c.recordOperation(fmt.Sprintf("%.2f - %.2f = %.2f", a, b, result))
	return result
}

func (c *Calculator) Multiply(a, b float64) float64 {
	result := a * b
	c.recordOperation(fmt.Sprintf("%.2f * %.2f = %.2f", a, b, result))
	return result
}

func (c *Calculator) Divide(a, b float64) (float64, error) {
	if b == 0 {
		return 0, errors.New("division by zero is not allowed")
	}
	result := a / b
	c.recordOperation(fmt.Sprintf("%.2f / %.2f = %.2f", a, b, result))
	return result, nil
}

func (c *Calculator) recordOperation(operation string) {
	c.History = append(c.History, operation)
}

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

func (c *Calculator) ClearHistory() {
	c.History = make([]string, 0)
	c.Logger.Info("History cleared")
}
