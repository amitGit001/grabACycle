package com.grabACycle.grabACycle.exception.messages;

public class JobExecutionErrorResponse {
    private String message;
    private int status;

    public JobExecutionErrorResponse() {
    }

    public JobExecutionErrorResponse(String message, int status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
