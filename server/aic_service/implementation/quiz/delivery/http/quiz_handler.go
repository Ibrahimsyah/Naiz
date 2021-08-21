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
	userGroup.POST("/quiz", handler.SubmitQuiz)
}

func (q *QuizHandler) SubmitQuiz(c echo.Context) error {
	submitQuiz := new(domain.SubmitQuizRequest)
	if err := c.Bind(submitQuiz); err != nil {
		return echo.NewHTTPError(http.StatusBadRequest, err.Error())
	}
	if err := c.Validate(submitQuiz); err != nil {
		return c.JSON(http.StatusBadRequest, err.Error())
	}

	token := c.Request().Header.Get("Authorization")
	extractedToken, err := middleware.NewExtractToken(token)
	if err != nil {
		return c.JSON(http.StatusBadRequest, custom_response.NewFailedResponse(err.Error()))
	}

	result, err := q.QuizUseCase.SubmitQuiz(extractedToken.UserId, submitQuiz)
	if err != nil {
		return c.JSON(http.StatusInternalServerError, custom_response.NewFailedResponse(err.Error()))
	}

	return c.JSON(http.StatusOK, custom_response.NewSucceedResponse("submitted successfully", result))
}
