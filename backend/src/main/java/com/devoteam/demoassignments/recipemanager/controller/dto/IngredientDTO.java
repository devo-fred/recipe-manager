package com.devoteam.demoassignments.recipemanager.controller.dto;

import javax.validation.constraints.NotEmpty;

public class IngredientDTO {

    @NotEmpty
    private String name;
    @NotEmpty
    private String amount;

    public IngredientDTO() {
    }

    public IngredientDTO( String name, String amount) {
        this.name = name;
        this.amount = amount;
    }

    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return String return the amount
     */
    public String getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }

}