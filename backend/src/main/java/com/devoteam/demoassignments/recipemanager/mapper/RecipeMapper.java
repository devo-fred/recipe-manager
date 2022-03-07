package com.devoteam.demoassignments.recipemanager.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.devoteam.demoassignments.recipemanager.controller.dto.RecipeDTO;
import com.devoteam.demoassignments.recipemanager.entity.Recipe;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class RecipeMapper {
    private ModelMapper modelMapper = new ModelMapper();
    
    public List<RecipeDTO> convertToDto(List<Recipe> recipes) {
        return recipes.stream().map(this::convertToDto).collect(Collectors.toList());
    }
    
    public RecipeDTO convertToDto(Recipe recipe) {
        return modelMapper.map(recipe,RecipeDTO.class);
    }
    
    public <T> Recipe convertToEntity(T recipeDTO) {
        return modelMapper.map(recipeDTO, Recipe.class);
    }

}