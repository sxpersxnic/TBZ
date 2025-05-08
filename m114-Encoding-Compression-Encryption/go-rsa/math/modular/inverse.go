package modular

import (
	"errors"

	"github.com/sxperlinx/TBZ/m114-Encoding-Compression-Encryption/go-rsa/math/gcd"
)

var ErrorInverse = errors.New("no Modular Inverse exists")

func Inverse(a, m int64) (int64, error) {
	gcd, x, _ := gcd.Extended(a, m)
	if gcd != 1 || m == 0 {
		return 0, ErrorInverse
	}

	return ((m + (x % m)) % m), nil
}