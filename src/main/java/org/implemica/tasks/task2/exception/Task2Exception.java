package org.implemica.tasks.task2.exception;

// used to display manual data input errors
public class Task2Exception extends RuntimeException {

    private final String message;

    public Task2Exception(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
