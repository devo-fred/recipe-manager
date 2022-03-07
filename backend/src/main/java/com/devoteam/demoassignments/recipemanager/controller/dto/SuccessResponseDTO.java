package com.devoteam.demoassignments.recipemanager.controller.dto;

import com.devoteam.demoassignments.recipemanager.exception.dto.ResponseDTO;

public class SuccessResponseDTO extends ResponseDTO {
    private Object payload;

    public SuccessResponseDTO (String message) {
        this.setMessage(message);
    }

    public SuccessResponseDTO (String message, Object payload) {
        this.setMessage(message);
        this.payload = payload;
    }

    /**
     * @return Object return the payload
     */
    public Object getPayload() {
        return payload;
    }

    /**
     * @param payload the payload to set
     */
    public void setPayload(Object payload) {
        this.payload = payload;
    }

}