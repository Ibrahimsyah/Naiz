package env

import (
	"os"

	"github.com/joho/godotenv"
)

type DriverAppService struct {
	AppPort   string
	AppName   string
	AppSecret string
}

func ReadDriverAppService() (DriverAppService, error) {
	if err := godotenv.Load(".env"); err != nil {
		return DriverAppService{}, err
	}

	return DriverAppService{
		AppPort:   os.Getenv("APP_PORT"),
		AppName:   os.Getenv("APP_NAME"),
		AppSecret: os.Getenv("APP_SECRET"),
	}, nil
}
