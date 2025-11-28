package exercise1

func Add(a, b float64) float64 {
	return a + b
}

func Subtract(a, b float64) float64 {
	return a - b
}

func Multiply(a, b float64) float64 {
	return a * b
}

func Divide(a, b float64) float64 {
	if b == 0 {
		panic("division by zero is not allowed")
	}
	return a / b
}