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

        pageErrorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        pageErrorResponse.setMessage(e.getMessage());

        return new ResponseEntity<>(pageErrorResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    public ResponseEntity<InvalidSortFieldErrorResponse> handleInvalidSortFieldException(InvalidSortFieldException e) {
        InvalidSortFieldErrorResponse errorResponse = new InvalidSortFieldErrorResponse();

        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage(e.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    public ResponseEntity<InvalidSortDirErrorResponse> handleInvalidSortDirException(InvalidSortDirException e) {
        InvalidSortDirErrorResponse errorResponse = new InvalidSortDirErrorResponse();

        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage(e.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    public ResponseEntity<CycleCreationErrorResponse> handleCycleCreationException(CycleCreationException e) {
        CycleCreationErrorResponse errorResponse = new CycleCreationErrorResponse();

        errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.setMessage(e.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<JobExecutionErrorResponse> handleJobExecutionException(JobExecutionException e) {
        JobExecutionErrorResponse errorResponse = new JobExecutionErrorResponse();

        errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.setMessage("Error executing the job: " + e.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<UpdateBookingStatusErrorResponse> handleUpdateBookingStatusException(UpdateBookingStatusException e)
    {
        UpdateBookingStatusErrorResponse errorResponse = new UpdateBookingStatusErrorResponse();
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());

        errorResponse.setMessage("Booking Failed: Cycle ID does not exist");

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

    }

//    @ExceptionHandler
//    public ResponseEntity<BadRequestErrorExceptionResponse> handleException(Exception e)
//    {
//        BadRequestErrorExceptionResponse errorResponse = new BadRequestErrorExceptionResponse();
//
//        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
//        errorResponse.setMessage("Wrong parameters passed");
//        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
//    }
}
