package http

import (
	"github.com/Ibrahimsyah/Naiz/domain"
	"github.com/Ibrahimsyah/Naiz/utility/custom_response"
	"github.com/labstack/echo/v4"
	"net/http"
)

type UserHanlder struct {
	UserUseCase domain.UserUseCase
}

func NewUserHanlder(e *echo.Echo, u domain.UserUseCase) {
	handler := &UserHanlder{UserUseCase: u}
	e.POST("/sign_up", handler.SignUp)
	e.POST("/sign_in", handler.SignIn)
}

func (u *UserHanlder) SignUp(c echo.Context) error {
	signUp := new(domain.UserSignUpRequest)
	if err := c.Bind(signUp); err != nil {
		return echo.NewHTTPError(http.StatusBadRequest, err.Error())
	}
	if err := c.Validate(signUp); err != nil {
		return echo.NewHTTPError(http.StatusBadRequest, err.Error())
	}

	if err := u.UserUseCase.CreateUser(signUp); err != nil {
		return echo.NewHTTPError(http.StatusInternalServerError, custom_response.NewFailedResponse(err.Error()))
	}
	return c.JSON(http.StatusOK, custom_response.NewSucceedResponse("sign up successfully", nil))
}

func (u *UserHanlder) SignIn(c echo.Context) error {
	queries := map[string]string{
		"select": c.QueryParam("column"),
	}

	signIn := new(domain.UserSignInRequest)
	if err := c.Bind(signIn); err != nil {
		return echo.NewHTTPError(http.StatusBadRequest, err.Error())
	}
	if err := c.Validate(signIn); err != nil {
		return echo.NewHTTPError(http.StatusBadRequest, err.Error())
	}

	result, err := u.UserUseCase.SignIn(signIn, queries)
	if err != nil {
		return echo.NewHTTPError(http.StatusInternalServerError, custom_response.NewFailedResponse(err.Error()))
	}
	return c.JSON(http.StatusOK, custom_response.NewSucceedResponse("sign in successfully", result))
}
