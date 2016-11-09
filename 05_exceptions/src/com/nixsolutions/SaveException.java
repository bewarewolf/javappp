package com.nixsolutions;

public class SaveException extends RuntimeException {
	
	private static final long serialVersionUID = 2042835560812735675L;

	public SaveException(String message) {
		super(message);
	}
	
	public SaveException(Throwable cause) {
		super(cause);
	}
}
