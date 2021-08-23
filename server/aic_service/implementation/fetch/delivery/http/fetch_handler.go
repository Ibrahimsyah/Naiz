package http

import (
	"github.com/Ibrahimsyah/Naiz/domain"
	"github.com/Ibrahimsyah/Naiz/utility/custom_response"
	"github.com/labstack/echo/v4"
	"net/http"
)

type FetchHandler struct {
	FetchUseCase domain.FetchUseCase
}

func NewFetchHandler(userGroup *echo.Group, fh domain.FetchUseCase) {
	handler := &FetchHandler{FetchUseCase: fh}
	userGroup.GET("/fetch/:table", handler.FetchValues)
}

func (f *FetchHandler) FetchValues(c echo.Context) error {
	table := c.Param("table")
	tempQueries := c.QueryParams()
	queries := map[string]string{}
	for i, val := range tempQueries {
		queries[i] = val[0]
	}

	result, err := f.FetchUseCase.FetchValues(table, queries)
	if err != nil {
		return c.JSON(http.StatusInternalServerError, custom_response.NewFailedResponse(err.Error()))
	}

	return c.JSON(http.StatusOK, custom_response.NewSucceedResponse("fetch data successfully", result))
}
