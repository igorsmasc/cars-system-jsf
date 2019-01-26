package br.com.carssystem.util.exception;

/**
 *
 * @author igors
 */
public class ErrorSystem extends Exception {

    public ErrorSystem(String message) {
        super(message);
    }

    public ErrorSystem(String message, Throwable cause) {
        super(message, cause);
    }

    
}
