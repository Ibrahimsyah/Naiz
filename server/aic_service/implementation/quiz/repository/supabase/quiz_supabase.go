package supabase

import (
	"encoding/json"
	"errors"
	"github.com/Ibrahimsyah/Naiz/domain"
	"github.com/Ibrahimsyah/Naiz/infrastructure/supabase"
	"io"
	"net/http"
)

type QuizRepositorySupabase struct {
	DB                   *supabase.DriverSupabase
	UserQuizPacksTable   string
	UserQuizAnswersTable string
}

func NewQuizRepositorySupabase(db *supabase.DriverSupabase) domain.QuizRepository {
	return &QuizRepositorySupabase{DB: db, UserQuizPacksTable: "user_quiz_packs", UserQuizAnswersTable: "user_quiz_answers"}
}

func (q *QuizRepositorySupabase) GetQuizHistory(userID int8) ([]map[string]interface{}, error) {
	//id,quiz_packs(id,title,description,level),user_quiz_answers(id,quiz_options(id,option,is_true,quiz_questions(id,question,image)))
	panic("implement me")
}

func (q *QuizRepositorySupabase) GetQuizHistoryByID(userID, userQuizID int8) (map[string]interface{}, error) {
	//id,quiz_packs(id,title,description,level),user_quiz_answers(id,quiz_options(id,option,is_true,quiz_questions(id,question,image)))
	panic("implement me")
}

func (q *QuizRepositorySupabase) SubmitUserQuizPack(userID, quizPackID int8) (map[string]interface{}, error) {
	create := map[string]interface{}{
		"user_id":      userID,
		"quiz_pack_id": quizPackID,
	}
	jsonByte, err := json.Marshal(create)
	if err != nil {
		return nil, err
	}

	req, err := q.DB.RequestFormula(q.UserQuizPacksTable, http.MethodPost, jsonByte)
	if err != nil {
		return nil, err
	}
	req.Header.Set("Prefer", "return=representation")

	response, err := q.DB.ExecuteRequestFormula(req)
	if err != nil {
		return nil, err
	}
	body, err := io.ReadAll(response.Body)
	if err != nil {
		return nil, err
	}
	var result []map[string]interface{}
	if err := json.Unmarshal(body, &result); err != nil {
		return nil, errors.New("gagal marshal satu")
	}
	if len(result) == 1 {
		return result[0], nil
	}

	return nil, errors.New("zero row affected")
}

func (q *QuizRepositorySupabase) SubmitUserQuizAnswer(userQuizPackID, quizOptionID int8) error {
	create := map[string]interface{}{
		"user_quiz_pack_id": userQuizPackID,
		"quiz_option_id":    quizOptionID,
	}
	jsonByte, err := json.Marshal(create)
	if err != nil {
		return err
	}

	req, err := q.DB.RequestFormula(q.UserQuizAnswersTable, http.MethodPost, jsonByte)
	if err != nil {
		return err
	}

	if _, err := q.DB.ExecuteRequestFormula(req); err != nil {
		return err
	}

	return nil
}
