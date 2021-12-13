package com.recipe.app.mongodb.repository.reaktive;

import com.recipe.app.mongodb.model.UnitOfMeasure;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface UnitOfMeasureReactiveRepository extends ReactiveMongoRepository<UnitOfMeasure,String> {
    Mono<UnitOfMeasure> findByDescription(String description);
}
