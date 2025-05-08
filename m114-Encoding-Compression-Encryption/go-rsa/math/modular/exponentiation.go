package modular;

import (
	"errors"
	"math"
)

var (
	ErrorIntOverflow = errors.New("Integer overflow")
	ErrorNegativeExponent = errors.New("Negative exponent")
)

func Exponentiation(base, exponent, mod int64) (int64, error) {
	if mod == 1 {
		return 0, nil
	}

	if exponent < 0 {
		return -1, ErrorNegativeExponent
	}

	_, err := Multiply64BitInt(mod-1, mod-1)

	if err != nil {
		return -1, err
	}

	var result int64 = 1
	base = base % mod

	for exponent > 0 {
		if exponent%2 == 1 {
			result = (result * base) % mod
		}
		exponent = exponent >> 1
		base = (base * base) % mod
	}

	return result, nil
}

func Multiply64BitInt(left, right int64) (int64, error) {
	if math.Abs(float64(left)) > float64(math.MaxInt64)/math.Abs(float64(right)) {
		return 0, ErrorIntOverflow
	}
	return left * right, nil
}