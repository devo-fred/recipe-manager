package com.devoteam.demoassignments.recipemanager.repository;

import com.devoteam.demoassignments.recipemanager.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

}