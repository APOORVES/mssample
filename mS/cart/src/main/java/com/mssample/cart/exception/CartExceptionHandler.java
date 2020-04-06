package com.mssample.cart.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.mssample.cart.model.ErrorMessage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class CartExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErrorMessage> handleValidationExceptions(MethodArgumentNotValidException ex) {
		log.error("Validation Error:" + ex.getMessage());
		List<ErrorMessage> errors = new ArrayList<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.add(new ErrorMessage(fieldName, errorMessage));
			log.error("Field Name:" + fieldName + " ErrorMessage:" + errorMessage);
		});
		return errors;
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(CartException.class)
	public ErrorMessage handleBusinessExceptions(CartException ex) {
		log.error("Business Error:" + ex.getMessage());
		ErrorMessage error = new ErrorMessage(ex.getErrorCode(), ex.getErrorMessage());
		return error;
	}
}
