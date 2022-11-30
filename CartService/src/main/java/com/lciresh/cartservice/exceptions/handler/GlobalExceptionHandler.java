package com.lciresh.cartservice.exceptions.handler;

import com.lciresh.cartservice.exceptions.DataExistingException;
import com.lciresh.cartservice.exceptions.IllegalPermissionException;
import com.lciresh.cartservice.exceptions.InvalidInputException;
import com.lciresh.cartservice.exceptions.NoDataAvailableException;
import com.lciresh.cartservice.exceptions.model.CommonExceptionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataExistingException.class)
    public ResponseEntity<Object> handleDataExistingException(DataExistingException e) {
        CommonExceptionModel commonExceptionModel = new CommonExceptionModel(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), "data.already-exist");

        return new ResponseEntity<>(commonExceptionModel, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoDataAvailableException.class)
    public ResponseEntity<Object> handleNoDataAvailableException(NoDataAvailableException e) {
        CommonExceptionModel commonExceptionModel = new CommonExceptionModel(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), "data.not-available");

        return new ResponseEntity<>(commonExceptionModel, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<Object> handleInvalidInputException(InvalidInputException e) {
        CommonExceptionModel commonExceptionModel = new CommonExceptionModel(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), "data.invalid-input");

        return new ResponseEntity<>(commonExceptionModel, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalPermissionException.class)
    public ResponseEntity<Object> handleIllegalPermissionException(IllegalPermissionException e) {
        CommonExceptionModel commonExceptionModel = new CommonExceptionModel(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), "data.invalid-input");

        return new ResponseEntity<>(commonExceptionModel, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
