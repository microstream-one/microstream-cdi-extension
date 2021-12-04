package one.microstream.cdi;

public class MicrostreamException extends RuntimeException {


    /**
     * creates a new ConfigException.
     *
     * @param message exception message
     * @param cause the cause
     */
    public MicrostreamException(String message, Throwable cause) {
        super(message, cause);
    }
    /**
     * creates a new ConfigException.
     *
     * @param message exception message
     */
    public MicrostreamException(String message) {
        super(message);
    }
}
