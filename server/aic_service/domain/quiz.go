package domain

type SubmitQuizRequest struct {
	QuizPackID int8 `json:"quiz_pack_id"`
	Result     int8 `json:"result"`
}

type QuizUseCase interface {
	// submit result quiz
	SubmitQuizResult(userID int8, submitQuizRequest *SubmitQuizRequest) (map[string]interface{}, error)
}

type QuizRepository interface {
	SubmitUserQuizPack(userID, quizPackID int8) (map[string]interface{}, error)
	SubmitUserQuizAnswer(userQuizPackID, quizOptionID int8) error
}
