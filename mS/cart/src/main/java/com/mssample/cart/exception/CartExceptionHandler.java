package com.mssample.cart.exception;

import static com.mssample.cart.common.CartConstants.UI_ERROR_SYSTEM;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.mssample.cart.common.CartProperties;
import com.mssample.cart.modelui.request.ErrorMessage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class CartExceptionHandler {
	
	/**
	 * Method for handling the Javax validation framework based errors (@Valid)
	 * @param ex Exception
	 * @return List of error messages
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErrorMessage> handleValidationExceptions(MethodArgumentNotValidException ex) {
		log.error("Validation Error:" + ex.getMessage());
		List<ErrorMessage> errors = new ArrayList<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String[] lookupKey = error.getCodes();
			String customMessage = CartProperties.getProperty(lookupKey[0]);
			String errorMessage = customMessage != null? customMessage : error.getDefaultMessage();
			errors.add(new ErrorMessage(fieldName, errorMessage));
			log.error("Field Name:" + fieldName + " ErrorMessage:" + errorMessage);
		});
		return errors;
	}
	
	/**
	 * Handles cart exception thrown anywhere in the code base 
	 * @param ex Exception
	 * @return Error Message
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(CartException.class)
	public ErrorMessage handleBusinessExceptions(CartException ex) {
		log.error("Business Error:" + ex.getMessage());
		ErrorMessage error = new ErrorMessage(ex.getErrorCode(), ex.getErrorMessage());
		return error;
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public ErrorMessage handleSystemExceptions(Exception ex) {
		log.error("General Exception:" + ex.getMessage());
		ErrorMessage error = new ErrorMessage(UI_ERROR_SYSTEM);
		return error;
	}
}
