package supabase

import (
	"encoding/json"
	"fmt"
	"github.com/Ibrahimsyah/Naiz/domain"
	"github.com/Ibrahimsyah/Naiz/infrastructure/supabase"
	"net/http"
)

type TempleRepositorySupabase struct {
	DB                 *supabase.DriverSupabase
	TempleReviewsTable string
	UserScansTable     string
}

func NewTempleRepositorySupabase(db *supabase.DriverSupabase) domain.TempleRepository {
	return &TempleRepositorySupabase{DB: db, TempleReviewsTable: "temple_reviews", UserScansTable: "user_scans"}
}

func (t *TempleRepositorySupabase) CreateReview(create map[string]string) error {
	jsonByte, err := json.Marshal(create)
	if err != nil {
		return err
	}

	req, err := t.DB.RequestFormula(t.TempleReviewsTable, http.MethodPost, jsonByte)
	if err != nil {
		return err
	}
	req.Header.Set("Prefer", "return=representation")
	fmt.Println(req)
	if _, err := t.DB.ExecuteRequestFormula(req); err != nil {
		return err
	}

	return nil
}

func (t *TempleRepositorySupabase) CreateScanRelief(create map[string]string) error {
	jsonByte, err := json.Marshal(create)
	if err != nil {
		return err
	}

	req, err := t.DB.RequestFormula(t.UserScansTable, http.MethodPost, jsonByte)
	if err != nil {
		return err
	}
	req.Header.Set("Prefer", "return=representation")

	if _, err := t.DB.ExecuteRequestFormula(req); err != nil {
		return err
	}

	return nil
}
