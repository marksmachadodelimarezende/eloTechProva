package com.elotech.scp.core.exceptionHandler;

public class MessageError {
    private String field;
    private Object value;
    private String message;

    public MessageError(String field, Object value, String message) {
        this.field = field;
        this.value = value;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public Object getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }
}
