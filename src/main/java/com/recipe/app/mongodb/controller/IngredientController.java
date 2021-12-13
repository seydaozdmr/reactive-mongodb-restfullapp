package com.recipe.app.mongodb.controller;

import com.recipe.app.mongodb.model.Ingredient;
import com.recipe.app.mongodb.service.IngredientService;
import com.recipe.app.mongodb.service.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class IngredientController {
    private final IngredientService ingredientService;
    private final RecipeService recipeService;

    public IngredientController(IngredientService ingredientService, RecipeService recipeService) {
        this.ingredientService = ingredientService;
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe/{id}/ingredients")
    public List<Ingredient> findAllIngredientsByRecipeId(@PathVariable String id){
        return ingredientService.findAllByRecipeId(id).blockFirst();
    }

    @GetMapping("/recipe/{id}/ingredients/{ingredientId}/show")
    public Ingredient findIngredientByRecipeIdAndIngredientId(@PathVariable String id,@PathVariable String ingredientId){
        return ingredientService.findByRecipeIdAndIngredientId(id,ingredientId).block();
    }

    @PostMapping("/recipe/{recipeId}/ingredients")
    public ResponseEntity<Ingredient> saveIngredient(@PathVariable String recipeId, @RequestBody Ingredient ingredient){
        return ResponseEntity.ok(ingredientService.saveIngredient(recipeId,ingredient).block());
    }

    @DeleteMapping("/recipe/{recipeId}/ingredients/{ingredientId}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable String recipeId,@PathVariable String ingredientId){
        return ResponseEntity.ok(ingredientService.deleteById(recipeId,ingredientId).block());
    }
}
