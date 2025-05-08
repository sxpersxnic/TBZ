package gcd;

func Recursive(a, b int64) int64 {
	if b == 0 {
		return a
	}
	return Recursive(b, a%b)
}

func Iterative(a, b int64) int64 {
	for b != 0 {
		a, b = b, a%b
	}
	return a
}