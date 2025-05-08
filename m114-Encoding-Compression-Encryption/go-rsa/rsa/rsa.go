package rsa

import (
	"errors"
	modular "github.com/sxperlinx/TBZ/m114-Encoding-Compression-Encryption/go-rsa/math/modular"
)

var ErrorFailedToEncrypt = errors.New("Failed to encrypt")
var ErrorFailedToDecrypt = errors.New("Failed to decrypt")

func Encrypt(message []rune, publicExponent, modulus int64) ([]rune, error) {
	var encrypted []rune

	for _, letter := range message {
		encryptedLetter, err := modular.Exponentiation(int64(letter), publicExponent, modulus)
		if err != nil {
			return nil, ErrorFailedToEncrypt
		}
		encrypted = append(encrypted, rune(encryptedLetter))
	}

	return encrypted, nil
}

func Decrypt(encrypted []rune, privateExponent, modulus int64) (string, error) {
	var decrypted []rune

	for _, letter := range encrypted {
		decryptedLetter, err := modular.Exponentiation(int64(letter), privateExponent, modulus)
		if err != nil {
			return "", ErrorFailedToDecrypt
		}
		decrypted = append(decrypted, rune(decryptedLetter))
	}

	return string(decrypted), nil
}