package custom_response

type CustomResponse struct {
	Success bool        `json:"success"`
	Message string      `json:"message"`
	Data    interface{} `json:"data"`
}

func NewSucceedResponse(messagge string, data interface{}) CustomResponse {
	return CustomResponse{
		Success: true,
		Message: messagge,
		Data:    data,
	}
}

func NewFailedResponse(messagge string) CustomResponse {
	return CustomResponse{
		Success: false,
		Message: messagge,
		Data:    nil,
	}
}
