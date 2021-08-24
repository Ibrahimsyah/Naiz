package http

import (
	"github.com/Ibrahimsyah/Naiz/domain"
	"github.com/Ibrahimsyah/Naiz/middleware"
	"github.com/Ibrahimsyah/Naiz/utility/custom_response"
	"github.com/labstack/echo/v4"
	"net/http"
)

type QuizHandler struct {
	QuizUseCase domain.QuizUseCase
}

func NewQuizHandler(userGroup *echo.Group, quc domain.QuizUseCase) {
	handler := &QuizHandler{QuizUseCase: quc}
	userGroup.POST("/quiz", handler.SubmitQuizResult)
}

func (q *QuizHandler) SubmitQuizResult(c echo.Context) error {
	authorization := c.Request().Header.Get("Authorization")
	token, err := middleware.NewExtractToken(authorization)
	if err != nil {
		return c.JSON(http.StatusBadRequest, custom_response.NewFailedResponse(err.Error()))
	}

	create := new(domain.SubmitQuizRequest)
	if err := c.Bind(create); err != nil {
		return c.JSON(http.StatusBadRequest,
			custom_response.NewFailedResponse(err.Error()))
	}
	if err := c.Validate(create); err != nil {
		return c.JSON(http.StatusBadRequest,
			custom_response.NewFailedResponse(err.Error()))
	}
	if err := q.QuizUseCase.SubmitQuizResult(token.UserId, create); err != nil {
		return c.JSON(http.StatusInternalServerError, custom_response.NewFailedResponse(err.Error()))
	}

	return c.JSON(http.StatusOK, custom_response.NewSucceedResponse("submit result successfully", nil))
}
