package com.mateuszgeborski.gradesbackend.domain.message;

public enum MessageContainer {
    RECEIVED("received"),
    SENT("sent"),
    DELETED("deleted");

    private final String messageContainer;

    private MessageContainer(final String messageContainer) {
        this.messageContainer = messageContainer;
    }

    public String get() {
        return messageContainer;
    }
}
