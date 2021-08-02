package com.elotech.scp.core.exceptionHandler.exception;

import com.elotech.scp.core.exceptionHandler.MessageError;

import java.util.List;

public class InvalidValueException extends RuntimeException {
    private List<MessageError> messageErrors;

    public InvalidValueException(List<MessageError> messageErrors, String causeMessage) {
        super(causeMessage);
        this.messageErrors = messageErrors;
    }

    public List<MessageError> getMessageErrors() {
        return messageErrors;
    }

}
