package vn.com.lol.thresh.commons.exceptions;

public class FileNotFoundException extends Exception {

    public FileNotFoundException(String message) {
        super(message);
    }

    public FileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
