package xyz.yogesh.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Entity already exists")
public class EntityAlreadyExistException extends RuntimeException {

	private static final long serialVersionUID = 5603051271523964879L;

	public EntityAlreadyExistException(String message) {
		super(message);
	}
}