package com.recipe.app.mongodb.service;

import com.recipe.app.mongodb.model.UnitOfMeasure;
import com.recipe.app.mongodb.repository.reaktive.UnitOfMeasureReactiveRepository;
import reactor.core.publisher.Flux;

public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {
    private final UnitOfMeasureReactiveRepository repository;

    public UnitOfMeasureServiceImpl(UnitOfMeasureReactiveRepository repository) {
        this.repository = repository;
    }

    @Override
    public Flux<UnitOfMeasure> findAll() {

        return repository.findAll();
    }
}
