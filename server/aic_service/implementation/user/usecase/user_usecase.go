package usecase

import (
	"fmt"
	"github.com/Ibrahimsyah/Naiz/domain"
	"github.com/Ibrahimsyah/Naiz/middleware"
	"github.com/Ibrahimsyah/Naiz/utility/custom_security"
)

type UserUseCase struct {
	UserRepositorySupabase  domain.UserRepository
	FetchRepositorySupabase domain.FetchRepository
}

func NewUserUseCase(ur domain.UserRepository, fr domain.FetchRepository) domain.UserUseCase {
	return &UserUseCase{UserRepositorySupabase: ur, FetchRepositorySupabase: fr}
}

func (u *UserUseCase) CreateUser(user *domain.UserSignUpRequest) error {
	hashedPassword, err := custom_security.NewHashingValue(user.Password)
	if err != nil {
		return err
	}
	create := map[string]interface{}{
		"name":     user.Name,
		"phone":    user.Phone,
		"password": hashedPassword,
		"email":    user.Email,
	}
	if err := u.UserRepositorySupabase.CreateUser(create); err != nil {
		return err
	}
	return nil
}

func (u *UserUseCase) SignIn(user *domain.UserSignInRequest, queries map[string]string) (map[string]interface{}, error) {
	queries["email"] = fmt.Sprintf("eq.%s", user.Email)

	tempResult, err := u.FetchRepositorySupabase.FetchValues("users", queries)
	if err != nil {
		return nil, err
	}
	result := tempResult[0]

	hashedPassword := result["password"].(string)
	if err := custom_security.NewValidatingValue(hashedPassword, user.Password); err != nil {
		return nil, err
	}

	token, err := middleware.NewJWT(10, result["name"].(string), result["email"].(string), int8(result["id"].(float64)))
	if err != nil {
		return nil, err
	}

	return map[string]interface{}{
		"id":    int8(result["id"].(float64)),
		"name":  result["name"].(string),
		"email": result["email"].(string),
		"phone": result["phone"].(string),
		"token": token,
	}, nil
}
