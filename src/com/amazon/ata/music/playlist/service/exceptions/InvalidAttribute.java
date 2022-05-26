package com.amazon.ata.music.playlist.service.exceptions;

public class InvalidAttribute extends RuntimeException {
    private static final long serialVersionUID = 8007453316698012851L;

    /**
     * Exception with no message or cause.
     */
    public InvalidAttribute() {
        super();
    }

    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public InvalidAttribute(String message) {
        super(message);
    }

    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public InvalidAttribute(Throwable cause) {
        super(cause);
    }

    /**
     * Exception with message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public InvalidAttribute(String message, Throwable cause) {
        super(message, cause);
    }

}
