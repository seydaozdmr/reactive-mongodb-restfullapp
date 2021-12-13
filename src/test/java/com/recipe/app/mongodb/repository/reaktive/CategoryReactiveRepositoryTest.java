package com.recipe.app.mongodb.repository.reaktive;

import com.recipe.app.mongodb.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class CategoryReactiveRepositoryTest {

    @Autowired
    CategoryReactiveRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll().block();
    }
    @Test
    void save(){
        Category category=new Category();
        category.setDescription("Foo");

        repository.save(category).block();

        Long count=repository.findAll().count().block();

        assertEquals(1,count);
    }

    @Test
    void findByDescription() {
        Category category=new Category();
        category.setDescription("Foo");

        repository.save(category).block();

        Category fetchedData=repository.findByDescription("Foo").block();

        assertNotNull(fetchedData);
    }
}