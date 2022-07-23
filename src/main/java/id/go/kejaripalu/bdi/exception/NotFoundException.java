package id.go.kejaripalu.bdi.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = -5438298798161107281L;

	public NotFoundException(String message) {
		super(message);
		log.error(message);
	}

}
