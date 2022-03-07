package com.devoteam.demoassignments.recipemanager.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import com.devoteam.demoassignments.recipemanager.controller.dto.AddRecipeDTO;
import com.devoteam.demoassignments.recipemanager.controller.dto.RecipeDTO;
import com.devoteam.demoassignments.recipemanager.controller.dto.SuccessResponseDTO;
import com.devoteam.demoassignments.recipemanager.entity.Recipe;
import com.devoteam.demoassignments.recipemanager.mapper.RecipeMapper;
import com.devoteam.demoassignments.recipemanager.service.RecipeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(RootConstants.ROOT_RECIPE)
public class RecipeController {
    private static final String ADD_RECIPE = "";
    private static final String GET_RECIPES = "";
    private static final String GET_RECIPE = "/{id}";
    private static final String UPDATE_RECIPE = "";
    private static final String DELETE_RECIPE = "/{id}";

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private RecipeMapper recipeMapper;

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping(path = GET_RECIPE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<SuccessResponseDTO> getRecipe(@PathVariable("id") Integer id) {
        Recipe recipe = recipeService.getRecipe(id);
        if (recipe == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recipe not found");
        }
        return ResponseEntity.ok().body(new SuccessResponseDTO("Get recipe successfully", recipeMapper.convertToDto(recipe)));
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping(path = GET_RECIPES, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<SuccessResponseDTO> getRecipes() {
        List<Recipe> recipes = recipeService.getRecipes();
        return ResponseEntity.ok()
                .body(new SuccessResponseDTO("Get all recipes successfully", recipeMapper.convertToDto(recipes)));
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping(path = ADD_RECIPE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<SuccessResponseDTO> addRecipe(@Valid @RequestBody AddRecipeDTO addRecipeDTO) {
        Recipe recipe = recipeMapper.convertToEntity(addRecipeDTO);
        Integer recipeId = recipeService.addRecipe(recipe);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{recipeId}")
                .buildAndExpand(recipeId)
                .toUri();
        return ResponseEntity.created(location)
                .body(new SuccessResponseDTO("Added new recipe with id " + recipeId + " successfully"));
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PutMapping(path = UPDATE_RECIPE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<SuccessResponseDTO> updateRecipe(@Valid @RequestBody RecipeDTO recipeDTO) {
        Recipe recipe = recipeMapper.convertToEntity(recipeDTO);
        Recipe updatedRecipe = recipeService.updateRecipe(recipe);
        if (updatedRecipe == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Update failed: Recipe with id " + recipeDTO.getId() + " not found");
        }
        RecipeDTO updatedRecipeDTO = recipeMapper.convertToDto(updatedRecipe);
        return ResponseEntity.ok()
                .body(new SuccessResponseDTO("Updated recipe with id " + recipe.getId() + " successfully", updatedRecipeDTO));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(path = DELETE_RECIPE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<SuccessResponseDTO> deleteRecipe(@PathVariable("id") Integer id) {
        if (!recipeService.deleteRecipe(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Deletion failed: Recipe with id " + id + " not found");
        }
        return ResponseEntity.ok().body(new SuccessResponseDTO("Deleted recipe with id " + id + " successfully"));
    }

}