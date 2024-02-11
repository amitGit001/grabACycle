package com.grabACycle.grabACycle.exception.messages;

public class InvalidSortDirErrorResponse {
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

    public InvalidSortDirErrorResponse() {
    }

    public InvalidSortDirErrorResponse(String message, String status) {
        this.message = message;
        this.status = status;
    }
}
