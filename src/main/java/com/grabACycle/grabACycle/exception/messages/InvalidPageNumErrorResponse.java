package com.grabACycle.grabACycle.exception.messages;

public class InvalidPageNumErrorResponse {
    private String message;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public InvalidPageNumErrorResponse() {

    }

    public InvalidPageNumErrorResponse(String message, String status) {
        this.message = message;
        this.status = status;
    }
}
