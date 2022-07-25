package id.go.kejaripalu.bdi.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import id.go.kejaripalu.bdi.exception.BDIErrorResponse;
import id.go.kejaripalu.bdi.exception.NotFoundException;

@ControllerAdvice
public class RestExceptionHandler {

	// Handling DataIntegrityViolationException
	@ExceptionHandler
	public ResponseEntity<BDIErrorResponse> handleException(DataIntegrityViolationException exception) {
		BDIErrorResponse response = new BDIErrorResponse(
				HttpStatus.BAD_REQUEST.value(), 
				"Data sudah pernah dimasukan!!!",
				System.currentTimeMillis() / 1000);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	// Handling NotFoundException
	@ExceptionHandler
	public ResponseEntity<BDIErrorResponse> handleException(NotFoundException exception) {
		BDIErrorResponse response = new BDIErrorResponse(
				HttpStatus.NOT_FOUND.value(), 
				exception.getMessage(),
				System.currentTimeMillis() / 1000);
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	
	// Handling Generic Exception
	@ExceptionHandler
	public ResponseEntity<BDIErrorResponse> handleException(Exception exception) {
		BDIErrorResponse response = new BDIErrorResponse(
				HttpStatus.BAD_REQUEST.value(), 
				exception.getMessage(),
				System.currentTimeMillis() / 1000);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
}
