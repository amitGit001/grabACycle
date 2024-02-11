package com.grabACycle.grabACycle.exception;

import com.grabACycle.grabACycle.exception.exceptions.*;
import com.grabACycle.grabACycle.exception.messages.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler
    public ResponseEntity<CycleErrorResponse> handleCycleException(CycleNotFoundException e){
        CycleErrorResponse errorResponse= new CycleErrorResponse();

        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(e.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<InvalidPageNumErrorResponse> handleInvalidPageNumberException(InvalidPageNumException e) {
       InvalidPageNumErrorResponse pageErrorResponse = new InvalidPageNumErrorResponse();

        pageErrorResponse.setStatus(String.valueOf(HttpStatus.BAD_REQUEST.value()));
        pageErrorResponse.setMessage(e.getMessage());

        return new ResponseEntity<>(pageErrorResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    public ResponseEntity<InvalidSortFieldErrorResponse> handleInvalidSortFieldException(InvalidSortFieldException e) {
        InvalidSortFieldErrorResponse errorResponse = new InvalidSortFieldErrorResponse();

        errorResponse.setStatus(String.valueOf(HttpStatus.BAD_REQUEST.value()));
        errorResponse.setMessage(e.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    public ResponseEntity<InvalidSortDirErrorResponse> handleInvalidSortDirException(InvalidSortDirException e) {
        InvalidSortDirErrorResponse errorResponse = new InvalidSortDirErrorResponse();

        errorResponse.setStatus(String.valueOf(HttpStatus.BAD_REQUEST.value()));
        errorResponse.setMessage(e.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    public ResponseEntity<CycleErrorResponse> handleCycleCreationException(CycleCreationException e) {
        CycleErrorResponse errorResponse = new CycleErrorResponse();

        errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.setMessage(e.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<JobExecutionErrorResponse> handleJobExecutionException(JobExecutionException e) {
        JobExecutionErrorResponse errorResponse = new JobExecutionErrorResponse();

        errorResponse.setStatus(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        errorResponse.setMessage("Error executing the job: " + e.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
