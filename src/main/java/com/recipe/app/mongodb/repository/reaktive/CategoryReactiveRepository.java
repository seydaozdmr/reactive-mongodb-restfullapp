package com.recipe.app.mongodb.repository.reaktive;

import com.recipe.app.mongodb.model.Category;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface CategoryReactiveRepository extends ReactiveMongoRepository<Category,String> {
    Mono<Category> findByDescription(String description);
}
