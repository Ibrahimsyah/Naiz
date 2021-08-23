package usecase

import (
	"github.com/Ibrahimsyah/Naiz/domain"
)

type QuizUseCase struct {
	QuizRepositorySupabase domain.QuizRepository
}

func NewQuizUseCase(qrs domain.QuizRepository) domain.QuizUseCase {
	return &QuizUseCase{QuizRepositorySupabase: qrs}
}

func (q *QuizUseCase) GetQuizHistory(userID int8) ([]map[string]interface{}, error) {
	panic("implement me")
}

func (q *QuizUseCase) GetQuizHistoryByID(userID, userQuizPackID int8) ([]map[string]interface{}, error) {
	panic("implement me")
}

func (q *QuizUseCase) SubmitQuiz(userID int8, submitQuizRequest *domain.SubmitQuizRequest) (map[string]interface{}, error) {
	result, err := q.QuizRepositorySupabase.SubmitUserQuizPack(userID, submitQuizRequest.QuizPackID)
	if err != nil {
		return nil, err
	}
	userQuizPackID := int8(result["id"].(float64))
	for _, val := range submitQuizRequest.SubmitQuizDetails {
		if err := q.QuizRepositorySupabase.SubmitUserQuizAnswer(userQuizPackID, val); err != nil {
			return nil, err
		}
	}

	return result, nil
}
