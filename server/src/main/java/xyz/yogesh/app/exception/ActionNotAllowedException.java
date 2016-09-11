package xyz.yogesh.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.BAD_REQUEST, reason = "Action not Allowed")
public class ActionNotAllowedException extends RuntimeException {
	
	private static final long serialVersionUID = -1871258646168804833L;

	public ActionNotAllowedException(String message) {
		super(message);
	}
}