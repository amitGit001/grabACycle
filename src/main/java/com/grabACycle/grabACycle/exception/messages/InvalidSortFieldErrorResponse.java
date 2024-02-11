package com.grabACycle.grabACycle.exception.messages;

public class InvalidSortFieldErrorResponse {
    private String message;
    private String status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public InvalidSortFieldErrorResponse() {
    }
    public InvalidSortFieldErrorResponse(String message, String status) {
        this.message = message;
        this.status = status;
    }


}
