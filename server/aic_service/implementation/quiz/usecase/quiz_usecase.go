package usecase

import (
	"github.com/Ibrahimsyah/Naiz/domain"
)

type QuizUseCase struct {
	QuizRepositorySupabase domain.QuizRepository
}

func (q *QuizUseCase) SubmitQuizResult(userID int8, submitQuizRequest *domain.SubmitQuizRequest) (map[string]interface{}, error) {
	panic("implement me")
}

func NewQuizUseCase(qrs domain.QuizRepository) domain.QuizUseCase {
	return &QuizUseCase{QuizRepositorySupabase: qrs}
}
