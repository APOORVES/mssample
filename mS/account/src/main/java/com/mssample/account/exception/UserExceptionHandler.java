package com.mssample.account.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mssample.account.model.ErrorMessage;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@ControllerAdvice
public class UserExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<ErrorMessage> userNotFoundException(UserNotFoundException e) {
		return error(BAD_REQUEST, e);
    }

    @ExceptionHandler({PasswordsNotSameException.class})
    public ResponseEntity<ErrorMessage> passwordsNotSameException(PasswordsNotSameException e) {
        return error(NOT_FOUND, e);
    }

    @ExceptionHandler({EmailAlreadyRegisteredException.class})
    public ResponseEntity<ErrorMessage> emailAlreadyRegisteredException(EmailAlreadyRegisteredException e) {
        return error(BAD_REQUEST, e);
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<ErrorMessage> exception(RuntimeException e) {
        return error(INTERNAL_SERVER_ERROR, e);
    }

    private ResponseEntity<ErrorMessage> error(HttpStatus status, Exception e) {
        log.error("Exception occurred during processing=", e);
    	ErrorMessage error = new ErrorMessage(status.value(), e.getMessage());
		return new ResponseEntity<>(error, status);
    }
}
