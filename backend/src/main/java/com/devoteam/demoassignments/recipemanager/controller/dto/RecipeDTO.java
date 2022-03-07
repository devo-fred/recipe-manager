package com.devoteam.demoassignments.recipemanager.controller.dto;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class RecipeDTO {
    @NotNull
    private Integer id;
    @NotEmpty
    private String name;
    @NotNull
    private Boolean vegetarian;
    @NotNull
    private Integer servings;
    @Valid
    @NotEmpty
    private List<IngredientDTO> ingredients;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public RecipeDTO() {
    }

    public RecipeDTO(Integer id, String name, Boolean vegetarian, Integer servings, List<IngredientDTO> ingredients) {
        this.id = id;
        this.name = name;
        this.vegetarian = vegetarian;
        this.servings = servings;
        this.ingredients = ingredients;
    }

    /**
     * @return Integer return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
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
     * @return LocalDateTime return the createdAt
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt the createdAt to set
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @return LocalDateTime return the updatedAt
     */
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    /**
     * @param updatedAt the updatedAt to set
     */
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
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
}