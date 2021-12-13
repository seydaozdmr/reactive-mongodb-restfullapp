package com.recipe.app.mongodb.service;

import com.recipe.app.mongodb.model.Recipe;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RecipeService {
    Flux<Recipe> getAll();
    Mono<Recipe> findById(String id);
    Mono<Recipe> update(String id,Recipe recipe);
    Mono<Recipe> save(Recipe recipe);
    Mono<Void> deleteById(String id);
}
