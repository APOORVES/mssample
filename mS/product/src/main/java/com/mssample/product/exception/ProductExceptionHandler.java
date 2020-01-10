package com.mssample.product.exception;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.mssample.product.model.ErrorMessage;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class ProductExceptionHandler {

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

    @ExceptionHandler({HttpMessageNotWritableException.class})
    public ResponseEntity<ErrorMessage> hHttpMessageNotWritableException(HttpMessageNotWritableException e) {
        return error(INTERNAL_SERVER_ERROR, e);
    }

    private ResponseEntity<ErrorMessage> error(HttpStatus status, Exception e) {
        log.error("Exception occurred during processing=", e);
    	ErrorMessage error = new ErrorMessage(status.value(), e.getMessage());
		return new ResponseEntity<>(error, status);
    }
}
