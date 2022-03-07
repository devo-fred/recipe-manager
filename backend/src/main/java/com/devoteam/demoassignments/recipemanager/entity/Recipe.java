package com.devoteam.demoassignments.recipemanager.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty
    private String name;
    @NotEmpty
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "recipe_directions", joinColumns = @JoinColumn(name = "id")) 
    @Column(name = "directions")
    private List<String> directions;
    @NotNull
    private Boolean vegetarian;
    @NotNull
    private Integer servings;
    @Valid
    @NotEmpty
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Ingredient> ingredients = new ArrayList<>();

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

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
     * @return List<Ingredient> return the ingredients
     */
    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    /**
     * @param ingredients the ingredients to set
     */
    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
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