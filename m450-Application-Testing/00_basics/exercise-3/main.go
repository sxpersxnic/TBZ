package main

import (
	"fmt"
)

func main() {
	if testCalculatePrice() {
		fmt.Println("[+] All tests passed!")
	} else {
		fmt.Println("[!] Some tests failed.")
	}
}

// calculatePrice calculates the final car price given base price, special model surcharge, extra equipment price,
// number of extras, and dealer discount. 
// The function applies discounts according to the following rules:
// 1. Dealer discount applies on the base price.
// 2. Extra discount applies only to extra equipment price: 
//    - 3-4 extras: 10% discount
//    - 5 or more extras: 15% discount
// 3. If dealer discount is higher than extra discount, extra discount takes the dealer discount value (original code logic)
func calculatePrice(basePrice, specialPrice, extraPrice float64, extras int, discount float64) float64 {
	var addonDiscount float64

	// Determine discount on extra equipment
	if extras >= 5 {
		addonDiscount = 15
	} else if extras >= 3 {
		addonDiscount = 10
	} else {
		addonDiscount = 0
	}

	// Bonus correction: The original code had a bug in the if-condition order.
	// It would incorrectly overwrite addonDiscount with discount even if discount < addonDiscount.
	if extras >= 3 && discount > addonDiscount {
		addonDiscount = discount
	}

	// Calculate final price
	// Correct formula: apply dealer discount on base price, add special price, apply extra discount on extras
	result := basePrice*(1-discount/100) + specialPrice + extraPrice*(1-addonDiscount/100)

	return result
}

// testCalculatePrice is a simple test driver for calculatePrice function
func testCalculatePrice() bool {
	testOK := true

	// Test cases: basePrice, specialPrice, extraPrice, extras, dealerDiscount, expectedPrice
	testCases := []struct {
		basePrice, specialPrice, extraPrice float64
		extras                              int
		discount                            float64
		expected                            float64
	}{
		{20000, 1000, 500, 2, 5, 20000*(1-0.05) + 1000 + 500},       // no extras discount
		{20000, 1000, 500, 3, 5, 20000*(1-0.05) + 1000 + 500*(1-0.10)}, // 3 extras → 10% discount on extras
		{20000, 1000, 500, 5, 5, 20000*(1-0.05) + 1000 + 500*(1-0.15)}, // 5 extras → 15% discount on extras
		{20000, 1000, 500, 4, 12, 20000*(1-0.12) + 1000 + 500*(1-0.12)}, // dealer discount higher than addonDiscount
	}

	for i, tc := range testCases {
		price := calculatePrice(tc.basePrice, tc.specialPrice, tc.extraPrice, tc.extras, tc.discount)
		if price != tc.expected {
			fmt.Printf("[x] Test %d failed: got %.2f, expected %.2f\n", i+1, price, tc.expected)
			testOK = false
		} else {
			fmt.Printf("[+] Test %d passed: %.2f\n", i+1, price)
		}
	}

	return testOK
}