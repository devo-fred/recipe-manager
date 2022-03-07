package com.devoteam.demoassignments.recipemanager.controller.dto;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AddRecipeDTO {
    @NotEmpty
    private String name;
    @NotEmpty
    private List<String> directions;
    @NotNull
    private Boolean vegetarian;
    @NotNull
    private Integer servings;
    @Valid
    @NotEmpty
    private List<IngredientDTO> ingredients;

    public AddRecipeDTO() {
    }

    public AddRecipeDTO(String name, Boolean vegetarian, Integer servings, List<IngredientDTO> ingredients) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.servings = servings;
        this.ingredients = ingredients;
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
     * @return Boolean return the vegetarian
     */
    public Boolean isVegetarian() {
        return vegetarian;
    }

    /**
     * @param vegetarian the vegetarian to set
     */
    public void setVegetarian(Boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    /**
     * @return Integer return the servings
     */
    public Integer getServings() {
        return servings;
    }

    /**
     * @param servings the servings to set
     */
    public void setServings(Integer servings) {
        this.servings = servings;
    }

    /**
     * @return List<IngredientDTO> return the ingredients
     */
    public List<IngredientDTO> getIngredients() {
        return ingredients;
    }

    /**
     * @param ingredients the ingredients to set
     */
    public void setIngredients(List<IngredientDTO> ingredients) {
        this.ingredients = ingredients;
    }

    /**
     * @return List<String> return the directions
     */
    public List<String> getDirections() {
        return directions;
    }

    /**
     * @param directions the directions to set
     */
    public void setDirections(List<String> directions) {
        this.directions = directions;
    }

}