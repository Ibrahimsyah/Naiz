package domain

type SubmitQuizRequest struct {
	QuizPackID int8 `json:"quiz_pack_id"`
	Result     int8 `json:"result"`
}

type QuizUseCase interface {
	// submit result quiz
	SubmitQuizResult(userID int8, submitQuizRequest *SubmitQuizRequest) error
}

type QuizRepository interface {
	SubmitQuizResult(create map[string]string) error
}
