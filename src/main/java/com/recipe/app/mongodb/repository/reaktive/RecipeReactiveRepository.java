package com.recipe.app.mongodb.repository.reaktive;

import com.recipe.app.mongodb.model.Recipe;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface RecipeReactiveRepository extends ReactiveMongoRepository<Recipe,String> {
}
