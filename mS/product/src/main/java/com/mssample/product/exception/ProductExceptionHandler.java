package com.mssample.product.exception;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mssample.product.model.ErrorMessage;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@ControllerAdvice
public class ProductExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<ErrorMessage> exception(RuntimeException e) {
        return error(INTERNAL_SERVER_ERROR, e);
    }

    @ExceptionHandler({ProductNotFoundException.class})
    public ResponseEntity<ErrorMessage> productNotFoundException(ProductNotFoundException e) {
        return error(NOT_FOUND, e);
    }

    @ExceptionHandler({DealNotFoundException.class})
    public ResponseEntity<ErrorMessage> dealNotFoundException(DealNotFoundException e) {
        return error(NOT_FOUND, e);
    }

    private ResponseEntity<ErrorMessage> error(HttpStatus status, Exception e) {
        log.error("Exception occurred during processing=", e);
    	ErrorMessage error = new ErrorMessage(status.value(), e.getMessage());
		return new ResponseEntity<>(error, status);
    }
}
