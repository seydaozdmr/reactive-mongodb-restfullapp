package com.recipe.app.mongodb.repository.reaktive;

import com.recipe.app.mongodb.model.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class UnitOfMeasureReactiveRepositoryTest {
    private static final String gram= "GRAM";
    @Autowired
    UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;

    @BeforeEach
    void setUp() {

        unitOfMeasureReactiveRepository.deleteAll().block();
    }

    @Test
    public void saveUnitOfMeasure(){
        UnitOfMeasure unitOfMeasure=new UnitOfMeasure();
        unitOfMeasure.setDescription(gram);

        unitOfMeasureReactiveRepository.save(unitOfMeasure).block();

        Long count=unitOfMeasureReactiveRepository.count().block();

        assertEquals(Long.valueOf(1L),count);
    }


    @Test
    public void findUomByDescription(){
        UnitOfMeasure unitOfMeasure=new UnitOfMeasure();
        unitOfMeasure.setDescription(gram);

        unitOfMeasureReactiveRepository.save(unitOfMeasure).block();

        UnitOfMeasure savedUnitOfMeasure=unitOfMeasureReactiveRepository.findByDescription(gram).block();

        assertEquals(gram,savedUnitOfMeasure.getDescription());
    }
}