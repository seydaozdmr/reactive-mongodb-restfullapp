package com.recipe.app.mongodb.service;

import com.recipe.app.mongodb.model.Ingredient;
import com.recipe.app.mongodb.model.Recipe;
import com.recipe.app.mongodb.model.UnitOfMeasure;
import com.recipe.app.mongodb.repository.reaktive.RecipeReactiveRepository;
import com.recipe.app.mongodb.repository.reaktive.UnitOfMeasureReactiveRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IngredientServiceImplTest {

    @Mock
    RecipeReactiveRepository recipeReactiveRepository;

    @Mock
    UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;

    @InjectMocks
    IngredientServiceImpl ingredientService;

    @BeforeEach
    void setUp() {

        ingredientService=new IngredientServiceImpl(recipeReactiveRepository,unitOfMeasureReactiveRepository);
    }

    @Test
    void findByRecipeIdAndIngredientId() {
        Recipe recipe = new Recipe();
        recipe.setId("1");

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId("1");

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId("1");

        Ingredient ingredient3 = new Ingredient();
        ingredient3.setId("3");

        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);
        recipe.addIngredient(ingredient3);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeReactiveRepository.findById(anyString())).thenReturn(Mono.just(recipe));

        //then
        Ingredient ingredient=ingredientService.findByRecipeIdAndIngredientId("1","3").block();

        assertEquals("3",ingredient.getId());
        verify(recipeReactiveRepository,times(1)).findById(anyString());

    }

    @Test
    void saveRecipe() {
        Recipe recipe = new Recipe();
        recipe.setId("1");
        Ingredient ingredient = new Ingredient();
        ingredient.setId("3");
        ingredient.setRecipe(recipe);
        ingredient.setUnitOfMeasure(new UnitOfMeasure());
        ingredient.getUnitOfMeasure().setId("1234");

        Recipe savedRecipe = new Recipe();
        savedRecipe.addIngredient(ingredient);
        savedRecipe.getIngredients().iterator().next().setId("3");

        when(recipeReactiveRepository.findById(anyString())).thenReturn(Mono.just(new Recipe()));
        when(recipeReactiveRepository.save(any())).thenReturn(Mono.just(savedRecipe));

        Ingredient savedIngredient = ingredientService.saveIngredient("1",ingredient).block();

        assertEquals("3",savedIngredient.getId());
        verify(recipeReactiveRepository, times(1)).findById(anyString());
        verify(recipeReactiveRepository, times(1)).save(any(Recipe.class));
    }

    @Test
    void findAllByRecipeId() {
    }

    @Test
    void deleteById() {
    }
}