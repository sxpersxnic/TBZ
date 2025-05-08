package lcm

import (
	"math"

	"github.com/sxperlinx/TBZ/m114-Encoding-Compression-Encryption/go-rsa/math/gcd"
)

func Lcm(a, b int64) int64 {
	return int64(math.Abs(float64(a*b)) / float64(gcd.Iterative(a, b)))
}