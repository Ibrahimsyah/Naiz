package supabase

import (
	"encoding/json"
	"errors"
	"fmt"
	"github.com/Ibrahimsyah/Naiz/domain"
	"github.com/Ibrahimsyah/Naiz/infrastructure/supabase"
	"io"
	"net/http"
)

type UserRepositorySupabase struct {
	DB    *supabase.DriverSupabase
	Table string
}

func NewUserRepositorySupabase(db *supabase.DriverSupabase) domain.UserRepository {
	return &UserRepositorySupabase{DB: db, Table: "users"}
}

func (u *UserRepositorySupabase) CreateUser(create map[string]interface{}) error {
	jsonByte, err := json.Marshal(create)
	if err != nil {
		return err
	}

	req, err := u.DB.RequestFormula(u.Table, http.MethodPost, jsonByte)
	if err != nil {
		return err
	}
	req.Header.Set("Prefer", "return=representation")

	if _, err := u.DB.ExecuteRequestFormula(req); err != nil {
		return err
	}

	return nil
}

func (u *UserRepositorySupabase) GetProfile(email, column string) (map[string]interface{}, error) {
	request, err := u.DB.RequestFormula(u.Table, http.MethodGet, nil)
	if err != nil {
		return nil, err
	}
	queries := request.URL.Query()
	queries.Add("select", column)
	queries.Add("email", fmt.Sprintf("eq.%s", email))
	request.URL.RawQuery = queries.Encode()

	res, err := u.DB.ExecuteRequestFormula(request)
	if err != nil {
		return nil, err
	}
	body, err := io.ReadAll(res.Body)
	var result []map[string]interface{}
	err = json.Unmarshal(body, &result)
	if len(result) == 0 {
		return nil, errors.New("entry not found")
	}

	return result[0], nil
}
