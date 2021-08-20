package supabase

import (
	"bytes"
	"fmt"
	"net/http"
	"os"

	"github.com/joho/godotenv"
	_ "github.com/lib/pq"
)

type DriverSupabase struct {
	Host       string
	Database   string
	Port       string
	User       string
	Password   string
	URL        string
	AnonKey    string
	ServiceKey string
}

func readEnvSupabase() (DriverSupabase, error) {
	if err := godotenv.Load(".env"); err != nil {
		return DriverSupabase{}, err
	}

	return DriverSupabase{
		Host:       os.Getenv("SUPABASE_HOST"),
		Database:   os.Getenv("SUPABASE_DATABASE"),
		Port:       os.Getenv("SUPABASE_PORT"),
		User:       os.Getenv("SUPABASE_USER"),
		Password:   os.Getenv("SUPABASE_PASSWORD"),
		URL:        os.Getenv("SUPABASE_URL"),
		AnonKey:    os.Getenv("SUPABASE_ANON_KEY"),
		ServiceKey: os.Getenv("SUPABASE_SERVICE_KEY"),
	}, nil
}

func NewDriverSupabase() (DriverSupabase, error) {
	result, err := readEnvSupabase()
	if err != nil {
		return DriverSupabase{}, err
	}
	return result, nil
}

func (s *DriverSupabase) RequestFormula(table, httpMethod string, jsonByte []byte) (*http.Request, error) {
	url := fmt.Sprintf("%s/%s",
		s.URL,
		table)

	var request *http.Request
	if httpMethod == http.MethodPost || httpMethod == http.MethodPatch {
		requestWithPayload, err := http.NewRequest(httpMethod, url, bytes.NewBuffer(jsonByte))
		if err != nil {
			return nil, err
		}
		requestWithPayload.Header.Set("Content-Type", "application/json")
		request = requestWithPayload
	} else {
		requestWithoutPayload, err := http.NewRequest(httpMethod, url, nil)
		if err != nil {
			return nil, err
		}
		request = requestWithoutPayload
	}

	request.Header.Set("apikey", s.ServiceKey)
	request.Header.Set("Authorization",
		fmt.Sprintf("%s %s", "Bearer", s.ServiceKey))
	return request, nil
}

func (s *DriverSupabase) ExecuteRequestFormula(request *http.Request) (*http.Response, error) {
	client := &http.Client{}
	result, err := client.Do(request)
	if err != nil {
		return nil, err
	}
	return result, nil
}
