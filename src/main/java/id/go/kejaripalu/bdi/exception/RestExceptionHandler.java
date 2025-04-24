package id.go.kejaripalu.bdi.exception;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

	// Handling DataIntegrityViolationException
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<BDIErrorResponse> handleException(DataIntegrityViolationException exception) {
		BDIErrorResponse response = new BDIErrorResponse(
				HttpStatus.BAD_REQUEST.value(), 
				"INVALID_DATA_INTEGRITY",
				System.currentTimeMillis() / 1000);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	// Handling NotFoundException
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<BDIErrorResponse> handleException(NotFoundException exception) {
		BDIErrorResponse response = new BDIErrorResponse(
				HttpStatus.NOT_FOUND.value(), 
				exception.getMessage(),
				System.currentTimeMillis() / 1000);
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

//	// Handling Expired JWT Exception
//	@ExceptionHandler(ExpiredJwtException.class)
//	public ResponseEntity<BDIErrorResponse> handleException(ExpiredJwtException exception) {
//		BDIErrorResponse response = new BDIErrorResponse(
//				HttpStatus.FORBIDDEN.value(),
//				exception.getMessage(),
//				System.currentTimeMillis() / 1000);
//		return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
//	}
	
	// Handling Generic Exception
	@ExceptionHandler({Exception.class})
	public ResponseEntity<BDIErrorResponse> handleException(Exception exception) {
		BDIErrorResponse response = new BDIErrorResponse(
				HttpStatus.BAD_REQUEST.value(), 
				exception.getMessage(),
				System.currentTimeMillis() / 1000);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
}
