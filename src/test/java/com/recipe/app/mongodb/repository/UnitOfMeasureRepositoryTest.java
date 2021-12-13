package com.recipe.app.mongodb.repository;

import com.recipe.app.mongodb.config.LoadData;
import com.recipe.app.mongodb.model.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class UnitOfMeasureRepositoryTest {

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    RecipeRepository recipeRepository;

    @BeforeEach
    void setUp() throws Exception {
        LoadData loadData=new LoadData(categoryRepository,unitOfMeasureRepository,recipeRepository);
        loadData.run(null);
    }

    @Test
    void findByDescription() {
        Optional<UnitOfMeasure> unitOfMeasure=unitOfMeasureRepository.findByDescription("Gram");

        assertEquals("Gram",unitOfMeasure.get().getDescription());
    }
}