package com.devoteam.demoassignments.recipemanager;

import static org.mockito.Mockito.when;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.devoteam.demoassignments.recipemanager.controller.RecipeController;
import com.devoteam.demoassignments.recipemanager.controller.RootConstants;
import com.devoteam.demoassignments.recipemanager.controller.dto.IngredientDTO;
import com.devoteam.demoassignments.recipemanager.controller.dto.AddRecipeDTO;
import com.devoteam.demoassignments.recipemanager.controller.dto.RecipeDTO;
import com.devoteam.demoassignments.recipemanager.entity.Recipe;
import com.devoteam.demoassignments.recipemanager.mapper.RecipeMapper;
import com.devoteam.demoassignments.recipemanager.service.RecipeService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ActiveProfiles(value = "test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@WebMvcTest(RecipeController.class)
public class RecipeControllerTest {
    private static IngredientDTO INGREDIENT_DTO_1;
    private static IngredientDTO INGREDIENT_DTO_2;
    private static IngredientDTO INGREDIENT_DTO_3;
    private static List<IngredientDTO> INGREDIENTS_DTO_1;
    private static List<IngredientDTO> INGREDIENTS_DTO_2;
    private static RecipeDTO RECIPE_DTO_1;
    private static RecipeDTO RECIPE_DTO_2;
    private static List<RecipeDTO> RECIPES_DTO_1;
    private static Recipe RECIPE_1;
    private static Recipe RECIPE_2;
    private static List<Recipe> RECIPES_1;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecipeService recipeService;

    @MockBean
    private RecipeMapper recipeMapper;

    @BeforeAll
    public void setUp() {
        INGREDIENT_DTO_1 = new IngredientDTO("Flour", "500 gr");
        INGREDIENT_DTO_2 = new IngredientDTO("Salt", "10 gr");
        INGREDIENT_DTO_3 = new IngredientDTO("Peper", "8 gr");
        INGREDIENTS_DTO_1 = new ArrayList<IngredientDTO>(Arrays.asList(INGREDIENT_DTO_1, INGREDIENT_DTO_2));
        INGREDIENTS_DTO_2 = new ArrayList<IngredientDTO>(Arrays.asList(INGREDIENT_DTO_1, INGREDIENT_DTO_3));
        RECIPE_DTO_1 = new RecipeDTO(1, "Bread", false, 4, INGREDIENTS_DTO_1);
        RECIPE_DTO_2 = new RecipeDTO(1, "BreadHot", false, 4, INGREDIENTS_DTO_2);
        RECIPES_DTO_1 = Arrays.asList(RECIPE_DTO_1, RECIPE_DTO_2);

        RECIPE_1 = new ModelMapper().map(RECIPE_DTO_1, Recipe.class);
        RECIPE_2 = new ModelMapper().map(RECIPE_DTO_2, Recipe.class);
        RECIPES_1 = Arrays.asList(RECIPE_1);
    }

    @Test
    public void getRecipeById() throws Exception {
        when(recipeMapper.convertToDto(Mockito.any(Recipe.class))).thenReturn(RECIPE_DTO_1);
        when(recipeService.getRecipe(Mockito.anyInt())).thenReturn(RECIPE_1);

        // when
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(RootConstants.ROOT_RECIPE + "/" + RECIPE_DTO_1.getId())
                .accept(APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
        // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("payload.name", is(RECIPE_DTO_1.getName())))
                .andExpect(jsonPath("payload.ingredients[0].name", is(INGREDIENT_DTO_1.getName())));
    }

    @Test
    public void getRecipes() throws Exception {
        when(recipeService.getRecipes()).thenReturn(RECIPES_1);
        when(recipeMapper.convertToDto(Mockito.anyList())).thenReturn(RECIPES_DTO_1);

        // when
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(RootConstants.ROOT_RECIPE)
                .accept(APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
        // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("payload[0].name", is(RECIPE_DTO_1.getName())))
                .andExpect(jsonPath("payload[0].ingredients[0].name", is(INGREDIENT_DTO_1.getName())))
                .andReturn();
    }

    @Test
    public void updateRecipe() throws Exception {
        when(recipeMapper.convertToEntity(Mockito.any(RecipeDTO.class))).thenReturn(RECIPE_2);
        when(recipeMapper.convertToDto(Mockito.any(Recipe.class))).thenReturn(RECIPE_DTO_2);
        when(recipeService.updateRecipe(Mockito.any(Recipe.class))).thenReturn(RECIPE_2);

        // when
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put(RootConstants.ROOT_RECIPE)
                .content(asJsonString(RECIPE_DTO_2))
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
        // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("payload.name", is(RECIPE_DTO_2.getName())))
                .andReturn();
    }

    @Test
    public void deleteRecipe() throws Exception {
        when(recipeService.deleteRecipe(Mockito.anyInt())).thenReturn(true);
        when(recipeMapper.convertToEntity(Mockito.any(RecipeDTO.class))).thenReturn(RECIPE_1);

        // when
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete(RootConstants.ROOT_RECIPE + "/" + RECIPE_DTO_1.getId())
                .accept(APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
        // then
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void addRecipe() throws Exception {
        when(recipeService.addRecipe(Mockito.any(Recipe.class))).thenReturn(3);
        when(recipeMapper.convertToEntity(Mockito.any(AddRecipeDTO.class))).thenReturn(RECIPE_1);

        // when
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(RootConstants.ROOT_RECIPE)
                .content(asJsonString(RECIPE_DTO_1))
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
        // then
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "http://localhost/api/recipe/3"))
                .andReturn();
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}