package http

import (
	"github.com/Ibrahimsyah/Naiz/domain"
	"github.com/labstack/echo/v4"
)

type QuizHandler struct {
	QuizUseCase domain.QuizUseCase
}

func NewQuizHandler(userGroup *echo.Group, quc domain.QuizUseCase) {
	//handler := &QuizHandler{QuizUseCase: quc}
}
