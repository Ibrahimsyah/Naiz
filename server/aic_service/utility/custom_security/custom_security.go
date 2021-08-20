package custom_security

import "golang.org/x/crypto/bcrypt"

func NewHashingValue(value string) (string, error) {
	p := []byte(value)
	bytes, err := bcrypt.GenerateFromPassword(p, bcrypt.DefaultCost)
	if err != nil {
		return "", err
	}
	hashedPassword := string(bytes)
	return hashedPassword, nil
}

func NewValidatingValue(hashedValue, targetValue string) error {
	source := []byte(hashedValue)
	target := []byte(targetValue)
	err := bcrypt.CompareHashAndPassword(source, target)
	return err
}
