package xyz.yogesh.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND, reason = "Entity not found")
public class EntityNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 4278346932395947562L;

	public EntityNotFoundException(String message) {
		super(message);
	}
}