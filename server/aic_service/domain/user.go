package domain

type UserSignUpRequest struct {
	Name     string `json:"name"`
	Email    string `json:"email"`
	Phone    string `json:"phone"`
	Password string `json:"password"`
}

type UserSignInRequest struct {
	Email    string `json:"email"`
	Password string `json:"password"`
}

type UserRepository interface {
	CreateUser(u map[string]interface{}) error
}

type UserUseCase interface {
	// change password
	// edit profile
	CreateUser(u *UserSignUpRequest) error
	SignIn(u *UserSignInRequest, queries map[string]string) (map[string]interface{}, error)
}
