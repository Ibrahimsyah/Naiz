package domain

type TempleRepository interface {
	CreateReview(create map[string]string) error
	CreateScanRelief(create map[string]string) error
}

type TempleUseCase interface {
	CreateReview(userID, templeID, rate int8) error
	CreateScanRelief(userID, templeID int8) error
}
