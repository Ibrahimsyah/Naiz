package route

import (
	_fetchHandler "github.com/Ibrahimsyah/Naiz/implementation/fetch/delivery/http"
	_fetchRepositorySupabase "github.com/Ibrahimsyah/Naiz/implementation/fetch/repository/supabase"
	_fetchUseCase "github.com/Ibrahimsyah/Naiz/implementation/fetch/usecase"
	_userHandler "github.com/Ibrahimsyah/Naiz/implementation/user/delivery/http"
	_userRepositorySupabase "github.com/Ibrahimsyah/Naiz/implementation/user/repository/supabase"
	_userUseCase "github.com/Ibrahimsyah/Naiz/implementation/user/usecase"
	"github.com/Ibrahimsyah/Naiz/infrastructure/supabase"
	"github.com/Ibrahimsyah/Naiz/utility/custom_response"
	"github.com/go-playground/validator"
	"github.com/labstack/echo/v4"
	echoMiddleware "github.com/labstack/echo/v4/middleware"
	"net/http"
)

type CustomValidator struct {
	validator *validator.Validate
}

func (cv *CustomValidator) Validate(i interface{}) error {
	return cv.validator.Struct(i)
}

func NewRoute(e *echo.Echo, db *supabase.DriverSupabase, serviceMiddleware echoMiddleware.JWTConfig) {
	e.Validator = &CustomValidator{validator: validator.New()}

	e.GET("/", func(context echo.Context) error {
		return context.JSON(http.StatusOK, custom_response.NewSucceedResponse("welcome to API, please contact the Administrator", nil))
	})

	userGroup := e.Group("/u", echoMiddleware.JWTWithConfig(serviceMiddleware))

	fetchRepositorySupabase := _fetchRepositorySupabase.NewFetchRepositorySupabase(db)
	userRepositorySupabase := _userRepositorySupabase.NewUserRepositorySupabase(db)

	userUseCase := _userUseCase.NewUserUseCase(userRepositorySupabase, fetchRepositorySupabase)
	fetchUseCase := _fetchUseCase.NewFetchUseCase(fetchRepositorySupabase)

	_userHandler.NewUserHanlder(e, userUseCase)
	_fetchHandler.NewFetchHandler(userGroup, fetchUseCase)
}
