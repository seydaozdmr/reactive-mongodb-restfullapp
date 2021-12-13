package com.recipe.app.mongodb.repository.reaktive;

import com.recipe.app.mongodb.model.Recipe;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.junit.jupiter.api.Assertions.*;


@DataMongoTest
class RecipeReactiveRepositoryTest {

    @Autowired
    RecipeReactiveRepository recipeReactiveRepository;


    @BeforeEach
    void setUp() {
        recipeReactiveRepository.deleteAll().block();
    }


    @Test
    public void testRecipeSave(){
        Recipe recipe=new Recipe();
        recipe.setDescription("Kuru fasülye");

        recipeReactiveRepository.save(recipe).block();

        Long count=recipeReactiveRepository.count().block();

        assertEquals(Long.valueOf(1L),count);

    }

    @Test
    void testFindByDescription(){
        Recipe recipe=new Recipe();
        recipe.setDescription("pilav");

        recipeReactiveRepository.save(recipe).block();

        Long count=recipeReactiveRepository.count().block();

        assertEquals(Long.valueOf(1),count);
    }
}