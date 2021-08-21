package domain

type SubmitQuizDetail struct {
	QuizOptionID int8 `json:"quiz_option_id"`
}

type SubmitQuizRequest struct {
	QuizPackID        int8               `json:"quiz_pack_id"`
	SubmitQuizDetails []SubmitQuizDetail `json:"submit_quiz_details"`
}

type QuizUseCase interface {
	SubmitQuiz(userID int8, submitQuizRequest *SubmitQuizRequest) (map[string]interface{}, error)
}

type QuizRepository interface {
	SubmitUserQuizPack(userID, quizPackID int8) (map[string]interface{}, error)
	SubmitUserQuizAnswer(userQuizPackID, quizOptionID int8) error
}
