package domain

import "github.com/golang-jwt/jwt"

type CustomClaimJWT struct {
	jwt.StandardClaims
	Email  string `json:"email"`
	UserId int8   `json:"user_id"`
}
