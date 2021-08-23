package http

import (
	"github.com/Ibrahimsyah/Naiz/domain"
	"github.com/Ibrahimsyah/Naiz/middleware"
	"github.com/Ibrahimsyah/Naiz/utility/custom_response"
	"github.com/labstack/echo/v4"
	"net/http"
	"strconv"
)

type TempleHandler struct {
	TempleUseCase domain.TempleUseCase
}

func NewTempleHandler(userGroup *echo.Group, t domain.TempleUseCase) {
	handler := &TempleHandler{TempleUseCase: t}
	userGroup.POST("/temple/review", handler.CreateReview)
	userGroup.POST("/temple/scan", handler.CreateScanRelief)
}

func (t *TempleHandler) CreateReview(c echo.Context) error {
	authorization := c.Request().Header.Get("Authorization")
	token, err := middleware.NewExtractToken(authorization)
	if err != nil {
		return c.JSON(http.StatusBadRequest, custom_response.NewFailedResponse(err.Error()))
	}
	templeID, err := strconv.Atoi(c.QueryParam("temple_id"))
	if err != nil {
		return c.JSON(http.StatusBadRequest, custom_response.NewFailedResponse(err.Error()))
	}
	rate, err := strconv.Atoi(c.QueryParam("rate"))
	if err != nil {
		return c.JSON(http.StatusBadRequest, custom_response.NewFailedResponse(err.Error()))
	}

	if err := t.TempleUseCase.CreateReview(token.UserId, int8(templeID), int8(rate)); err != nil {
		return c.JSON(http.StatusInternalServerError, custom_response.NewFailedResponse(err.Error()))
	}

	return c.JSON(http.StatusOK, custom_response.NewSucceedResponse("create review successfully", nil))
}

func (t *TempleHandler) CreateScanRelief(c echo.Context) error {
	authorization := c.Request().Header.Get("Authorization")
	token, err := middleware.NewExtractToken(authorization)
	if err != nil {
		return c.JSON(http.StatusOK, custom_response.NewFailedResponse(err.Error()))
	}
	templeID, err := strconv.Atoi(c.QueryParam("temple_id"))
	if err != nil {
		return c.JSON(http.StatusBadRequest, custom_response.NewFailedResponse(err.Error()))
	}

	if err := t.TempleUseCase.CreateScanRelief(token.UserId, int8(templeID)); err != nil {
		return c.JSON(http.StatusInternalServerError, custom_response.NewFailedResponse(err.Error()))
	}

	return c.JSON(http.StatusOK, custom_response.NewSucceedResponse("create scan successfully", nil))
}
