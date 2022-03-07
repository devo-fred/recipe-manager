package com.devoteam.demoassignments.recipemanager.repository;

import com.devoteam.demoassignments.recipemanager.entity.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, Integer> {

}