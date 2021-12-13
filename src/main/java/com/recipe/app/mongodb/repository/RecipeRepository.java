package com.recipe.app.mongodb.repository;

import com.recipe.app.mongodb.model.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe,String> {
}
