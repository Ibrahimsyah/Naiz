package usecase

import (
	"fmt"
	"github.com/Ibrahimsyah/Naiz/domain"
)

type QuizUseCase struct {
	QuizRepositorySupabase domain.QuizRepository
}

func NewQuizUseCase(qrs domain.QuizRepository) domain.QuizUseCase {
	return &QuizUseCase{QuizRepositorySupabase: qrs}
}

func (q *QuizUseCase) SubmitQuizResult(userID int8, submitQuizRequest *domain.SubmitQuizRequest) error {
	create := map[string]string{
		"user_id":      fmt.Sprintf("%d", userID),
		"result":       fmt.Sprintf("%d", submitQuizRequest.Result),
		"quiz_pack_id": fmt.Sprintf("%d", submitQuizRequest.QuizPackID),
	}
	if err := q.QuizRepositorySupabase.SubmitQuizResult(create); err != nil {
		return err
	}
	return nil
}
