package supabase

import (
	"encoding/json"
	"github.com/Ibrahimsyah/Naiz/domain"
	"github.com/Ibrahimsyah/Naiz/infrastructure/supabase"
	"net/http"
)

type QuizRepositorySupabase struct {
	DB                   *supabase.DriverSupabase
	UserQuizPacksTable   string
	UserQuizAnswersTable string
}

func NewQuizRepositorySupabase(db *supabase.DriverSupabase) domain.QuizRepository {
	return &QuizRepositorySupabase{DB: db, UserQuizPacksTable: "user_quiz_packs"}
}

func (q *QuizRepositorySupabase) SubmitQuizResult(create map[string]string) error {
	jsonByte, err := json.Marshal(create)
	if err != nil {
		return err
	}

	req, err := q.DB.RequestFormula(q.UserQuizPacksTable, http.MethodPost, jsonByte)
	if err != nil {
		return err
	}

	if _, err := q.DB.ExecuteRequestFormula(req); err != nil {
		return err
	}

	return nil
}
