package com.recipe.app.mongodb.service;

import com.recipe.app.mongodb.model.Recipe;
import com.recipe.app.mongodb.repository.RecipeRepository;
import com.recipe.app.mongodb.repository.reaktive.RecipeReactiveRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {
    private final RecipeReactiveRepository repository;

    public RecipeServiceImpl(RecipeReactiveRepository repository) {
        this.repository = repository;
    }

    @Override
    public Flux<Recipe> getAll() {
        return repository.findAll();
    }

    @Override
    public Mono<Recipe> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Mono<Recipe> update(String id, Recipe recipe) {
        Recipe recipe1=repository.findById(id).block();
        if(recipe1!=null){
            recipe1.updateRecipe(recipe);
            return repository.save(recipe1);
        }else{
            return repository.save(recipe);
        }
    }

    @Override
    public Mono<Recipe> save(Recipe recipe) {
        return repository.save(recipe);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        repository.deleteById(id).block();
        return Mono.empty();
    }
}
