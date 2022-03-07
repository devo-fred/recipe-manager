package com.devoteam.demoassignments.recipemanager.service;

import java.util.List;
import java.util.Optional;

import com.devoteam.demoassignments.recipemanager.entity.Recipe;
import com.devoteam.demoassignments.recipemanager.repository.RecipeRepository;
import com.devoteam.demoassignments.recipemanager.util.UserHelper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class RecipeService {
    private static final Logger logger = LoggerFactory.getLogger(RecipeService.class);

    @Autowired
    RecipeRepository recipeRepository;

    @Transactional
    public Integer addRecipe(Recipe newRecipe) {
        logger.info("User: {} - Add new recipe", UserHelper.getCurrentUsername());
        Recipe recipe = recipeRepository.save(newRecipe);
        return recipe.getId();
    }

    @Transactional
    public Recipe getRecipe(Integer id) {
        logger.info("User: {} - Get recipe with id {}", UserHelper.getCurrentUsername(),id);
        Optional<Recipe> recipe = recipeRepository.findById(id);
        if (!recipe.isPresent()) {
            return null;    
        }
        return recipe.get();
    }

    @Transactional
    public List<Recipe> getRecipes() {
        logger.info("User: {} - Get all recipes", UserHelper.getCurrentUsername());
        return recipeRepository.findAll();
    }

    @Transactional
    public Recipe updateRecipe(Recipe updatedRecipe) {
        logger.info("User: {} - Update recipe with id {}", UserHelper.getCurrentUsername(), updatedRecipe.getId());
        Optional<Recipe> recipe = recipeRepository.findById(updatedRecipe.getId());
        if (!recipe.isPresent()) {
            return null;
        }
        return recipeRepository.save(updatedRecipe);
    }
    
    @Transactional
    public boolean deleteRecipe(Integer id) {
        logger.info("User: {} - Delete recipe with id {}", UserHelper.getCurrentUsername(), id);
        if (recipeRepository.existsById(id)) {
            recipeRepository.deleteById(id);
            return true;
        }
        return false;
    }

}