package com.recipe.app.mongodb.service;

import com.recipe.app.mongodb.model.Ingredient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IngredientService {
    Mono<Ingredient> findByRecipeIdAndIngredientId(String recipeId,String ingredientId);
    Mono<Ingredient> saveIngredient(String recipeId,Ingredient ingredient);
    Flux<List<Ingredient>> findAllByRecipeId(String recipeId);
    Mono<Void> deleteById(String recipeId, String idToDelete);
}
