package usecase

import (
	"fmt"
	"github.com/Ibrahimsyah/Naiz/domain"
)

type TempleUseCase struct {
	TempleRepositorySupabase domain.TempleRepository
}

func NewTempleUseCase(t domain.TempleRepository) domain.TempleUseCase {
	return &TempleUseCase{TempleRepositorySupabase: t}
}

func (t *TempleUseCase) CreateReview(userID, templeID, rate int8) error {
	create := map[string]string{
		"user_id":   fmt.Sprintf("%d", userID),
		"temple_id": fmt.Sprintf("%d", templeID),
		"rate":      fmt.Sprintf("%d", rate),
	}
	return t.TempleRepositorySupabase.CreateReview(create)
}

func (t *TempleUseCase) CreateScanRelief(userID, templeID int8) error {
	create := map[string]string{
		"user_id":   fmt.Sprintf("%d", userID),
		"temple_id": fmt.Sprintf("%d", templeID),
	}
	return t.TempleRepositorySupabase.CreateScanRelief(create)
}
