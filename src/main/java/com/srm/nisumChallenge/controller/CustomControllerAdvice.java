package com.srm.nisumChallenge.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.srm.nisumChallenge.Exceptions.CustomDataConstraintException;
import com.srm.nisumChallenge.Exceptions.CustomNoValidPasswordException;
import com.srm.nisumChallenge.dto.response.ErrorResponse;

/**
 * Nisum Challenge
 * Case encargada de capturar las excepciones y enviar al usuario en un lenguaje
 * entendible
 * 
 * @author Sebastian Retamal Morales
 * @since 2023-12-15
 * @version 0.0.1-SNAPSHOT
 */
@ControllerAdvice
public class CustomControllerAdvice {
	private static final Logger logger = LogManager.getLogger(CustomControllerAdvice.class);

	/**
	 * @param e
	 * @param request
	 * @return 400 - BAD_REQUEST
	 */
	@ExceptionHandler(value = { MethodArgumentNotValidException.class })
	protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
			WebRequest request) {

		logger.error(e);

// TODO: en versiones posteriores se agregara mas detalles a los mensajes de error

		Map<String, String> errors = new HashMap<>();
		e.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});

		return new ResponseEntity<>(new ErrorResponse("Not Valid Argument",errors), HttpStatus.BAD_REQUEST);

	}
	
	/**
	 * 
	 * @param e
	 * @param request
	 * @return 409 - CONFLICT
	 */
	@ExceptionHandler(value = { CustomDataConstraintException.class })
	protected ResponseEntity<ErrorResponse> handleCustomDataConstraintException(CustomDataConstraintException e,
			WebRequest request) {
		
		logger.error(e);
		
		return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.CONFLICT);
		
	}
	
	
	
	@ExceptionHandler(value = { CustomNoValidPasswordException.class })
	protected ResponseEntity<ErrorResponse> handleCustomNoValidPasswordException(CustomNoValidPasswordException e,
			WebRequest request) {
		
		logger.error(e);
		
		return new ResponseEntity<>(new ErrorResponse("El correo ya registrado."), HttpStatus.CONFLICT);
		
	}
}
