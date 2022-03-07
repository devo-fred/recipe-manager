package com.devoteam.demoassignments.recipemanager.exception.dto;

import java.time.LocalDateTime;

public class ResponseDTO {
    private final LocalDateTime timestamp = LocalDateTime.now();
    private String message;

    public ResponseDTO() {
    }

    public ResponseDTO(String message) {
        this.message = message;
    }

    /**
     * @return LocalDateTime return the timestamp
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * @return String return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

}