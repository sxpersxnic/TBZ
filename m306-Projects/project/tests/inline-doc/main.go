package main

import "fmt"

// main is the entry point of the application.
// It demonstrates the usage of the Calculator and Logger types by performing
// various arithmetic operations and logging the results.
func main() {
	logger, err := NewLogger("INFO")

	if err != nil {
		println("Error creating logger:", err.Error())
		return
	}

	logger.Info("Application started")

	calc := NewCalculator()
	logger.Info("Calculator initialized")

	result1 := calc.Add(10, 5)
	logger.Info("Addition result: " + formatFloat(result1))

	result2 := calc.Subtract(20, 8)
	logger.Info("Subtraction result: " + formatFloat(result2))

	result3 := calc.Multiply(6, 7)
	logger.Info("Multiplication result: " + formatFloat(result3))

	result4, err := calc.Divide(100, 4)
	if err != nil {
		logger.Error("Division failed: " + err.Error())
	} else {
		logger.Info("Division result: " + formatFloat(result4))
	}

	_, err = calc.Divide(10, 0)
	if err != nil {
		logger.Warn("Expected error: " + err.Error())
	}

	calc.ShowHistory()

	logger.Info("Application finished")
}

// formatFloat formats a float64 number to a string with two decimal places.
func formatFloat(f float64) string {
	return fmt.Sprintf("%.2f", f)
}
