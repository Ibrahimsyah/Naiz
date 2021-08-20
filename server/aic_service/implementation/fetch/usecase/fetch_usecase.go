package usecase

import "github.com/Ibrahimsyah/Naiz/domain"

type FetchUseCase struct {
	FetchRepositorySupabase domain.FetchRepository
}

func NewFetchUseCase(fr domain.FetchRepository) domain.FetchUseCase {
	return &FetchUseCase{FetchRepositorySupabase: fr}
}

func (f *FetchUseCase) FetchValues(table string, queryMaps map[string]string) ([]map[string]interface{}, error) {
	result, err := f.FetchRepositorySupabase.FetchValues(table, queryMaps)
	if err != nil {
		return nil, err
	}
	return result, nil
}
