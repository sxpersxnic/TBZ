package exercise1

import "testing"

func TestAdd(t *testing.T) {
	result := Add(2, 3)
	expected := 5.0

	if result != expected {
		t.Errorf("Add(2, 3) = %v; want %v", result, expected)
	}
}

func TestSubtract(t *testing.T) {
	result := Subtract(5, 3)
	expected := 2.0

	if result != expected {
		t.Errorf("Subtract(5, 3) = %v; want %v", result, expected)
	}
}

func TestMultiply(t *testing.T) {
	result := Multiply(2, 3)
	expected := 6.0

	if result != expected {
		t.Errorf("Multiply(2, 3) = %v; want %v", result, expected)
	}
}

func TestDivide(t *testing.T) {
	result := Divide(6, 3)
	expected := 2.0

	if result != expected {
		t.Errorf("Divide(6, 3) = %v; want %v", result, expected)
	}
}

func TestDivideByZero(t *testing.T) {
	defer func() {
		if r := recover(); r == nil {
			t.Errorf("Divide did not panic on division by zero")
		}
	}()

	Divide(6, 0)
}