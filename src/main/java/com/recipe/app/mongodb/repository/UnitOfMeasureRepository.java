package com.recipe.app.mongodb.repository;

import com.recipe.app.mongodb.model.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure,String> {
    Optional<UnitOfMeasure> findByDescription(String description);
}
