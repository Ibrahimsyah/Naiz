package supabase

import (
	"encoding/json"
	"github.com/Ibrahimsyah/Naiz/domain"
	"github.com/Ibrahimsyah/Naiz/infrastructure/supabase"
	"io"
	"net/http"
)

type FetchRepositorySupabase struct {
	DB *supabase.DriverSupabase
}

func NewFetchRepositorySupabase(db *supabase.DriverSupabase) domain.FetchRepository {
	return &FetchRepositorySupabase{DB: db}
}

func (f *FetchRepositorySupabase) FetchValues(table string, queryMaps map[string]string) ([]map[string]interface{}, error) {
	request, err := f.DB.RequestFormula(table, http.MethodGet, nil)
	if err != nil {
		return nil, err
	}
	queries := request.URL.Query()
	for i, val := range queryMaps {
		queries.Add(i, val)
	}
	request.URL.RawQuery = queries.Encode()

	res, err := f.DB.ExecuteRequestFormula(request)
	if err != nil {
		return nil, err
	}
	body, err := io.ReadAll(res.Body)
	if err != nil {
		return nil, err
	}
	var result []map[string]interface{}
	if err := json.Unmarshal(body, &result); err != nil {
		return nil, err
	}

	return result, nil
}
