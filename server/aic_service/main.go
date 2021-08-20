package main

import (
	"fmt"
	appService "github.com/Ibrahimsyah/Naiz/infrastructure/app_service"
	"github.com/Ibrahimsyah/Naiz/infrastructure/supabase"
	"github.com/Ibrahimsyah/Naiz/middleware"
	"github.com/Ibrahimsyah/Naiz/route"
	"github.com/labstack/echo/v4"
	echoMiddleware "github.com/labstack/echo/v4/middleware"
)

func main() {
	e := echo.New()
	e.Use(echoMiddleware.CORS())

	serviceDriver, err := appService.ReadDriverAppService()
	if err != nil {
		panic(err)
	}

	supabaseDriver, err := supabase.NewDriverSupabase()
	if err != nil {
		panic(err)
	}

	authMiddleware, err := middleware.NewAuthMiddleware()
	if err != nil {
		panic(err)
	}

	route.NewRoute(e, &supabaseDriver, authMiddleware)

	e.Logger.Fatal(e.Start(fmt.Sprintf(":%s", serviceDriver.AppPort)))
}
