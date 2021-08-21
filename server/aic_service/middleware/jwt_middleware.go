package middleware

import (
	"github.com/Ibrahimsyah/Naiz/domain"
	appService "github.com/Ibrahimsyah/Naiz/infrastructure/app_service"
	"github.com/golang-jwt/jwt"
	"github.com/labstack/echo/v4/middleware"
	"strings"
	"time"
)

func NewJWT(_ time.Duration, name, email string, id int8) (string, error) {
	app, err := appService.ReadDriverAppService()
	if err != nil {
		return "", nil
	}

	//expUNIX := time.Now().Add(time.Hour * exp).Unix()
	claims := &domain.CustomClaimJWT{
		StandardClaims: jwt.StandardClaims{
			Issuer:  "AIC_NAIZ",
			Subject: name,
		},
		UserId: id,
		Email:  email,
	}
	token := jwt.NewWithClaims(jwt.SigningMethodHS256, claims)
	secret := []byte(app.AppSecret)
	t, err := token.SignedString(secret)
	if err != nil {
		return "", err
	}
	return t, nil
}

func _(hashedToken string) (*domain.CustomClaimJWT, error) {
	app, err := appService.ReadDriverAppService()
	if err != nil {
		return nil, err
	}

	hashedToken = strings.Replace(hashedToken, "Bearer ", "", 1)
	token, err := jwt.Parse(hashedToken, func(token *jwt.Token) (interface{}, error) {
		return []byte(app.AppSecret), nil
	})
	if err != nil {
		return nil, err
	}

	tokenClaims := token.Claims
	claims := tokenClaims.(jwt.MapClaims)

	return &domain.CustomClaimJWT{
		Email:  claims["email"].(string),
		UserId: int8(claims["user_id"].(float64)),
	}, nil
}

func NewAuthMiddleware() (middleware.JWTConfig, error) {
	envAPP, err := appService.ReadDriverAppService()
	if err != nil {
		return middleware.JWTConfig{}, err
	}
	secret := []byte(envAPP.AppSecret)
	isAuthorized := middleware.JWTConfig{
		SigningKey: secret,
	}
	return isAuthorized, nil
}
