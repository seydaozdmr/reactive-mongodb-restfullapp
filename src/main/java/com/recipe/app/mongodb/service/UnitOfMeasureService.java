package com.recipe.app.mongodb.service;

import com.recipe.app.mongodb.model.UnitOfMeasure;
import reactor.core.publisher.Flux;

public interface UnitOfMeasureService {
    Flux<UnitOfMeasure> findAll();
}
