package domain

type FetchRepository interface {
	FetchValues(table string, queryMaps map[string]string) ([]map[string]interface{}, error)
}

type FetchUseCase interface {
	FetchValues(table string, queryMaps map[string]string) ([]map[string]interface{}, error)
}
