package com.recipe.app.mongodb.controller;

import com.recipe.app.mongodb.model.Ingredient;
import com.recipe.app.mongodb.model.Recipe;
import com.recipe.app.mongodb.service.IngredientService;
import com.recipe.app.mongodb.service.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/recipe")
public class RecipeController {
    private final RecipeService recipeService;
    private final IngredientService ingredientService;

    public RecipeController(RecipeService recipeService, IngredientService ingredientService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }

    //sending data reactive Flux
    @GetMapping("")
    public ResponseEntity<Flux<Recipe>> getAll(){
        return ResponseEntity.ok(recipeService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> findRecipe(@PathVariable String id){
        return ResponseEntity.ok(recipeService.findById(id).block());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable String id,@RequestBody Recipe recipe){
        return ResponseEntity.ok(recipeService.update(id,recipe).block());
    }

    @PostMapping("")
    public ResponseEntity<Recipe> saveRecipe(@RequestBody Recipe recipe){
        return ResponseEntity.ok(recipeService.save(recipe).block());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable String id){
        return ResponseEntity.ok(recipeService.deleteById(id).block());
    }
}
