package com.devoteam.demoassignments.recipemanager.exception.dto;

import java.util.Map;

public class ValidationErrorDTO extends ResponseDTO{
    private Map<String, String> fieldErrors;

    /**
     * @return Map<String, String> return the fieldErrors
     */
    public Map<String, String> getFieldErrors() {
        return fieldErrors;
    }

    /**
     * @param fieldErrors the fieldErrors to set
     */
    public void setFieldErrors(Map<String, String> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

}