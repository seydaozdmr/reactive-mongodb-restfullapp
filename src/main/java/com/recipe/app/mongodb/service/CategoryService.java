package com.recipe.app.mongodb.service;

import com.recipe.app.mongodb.model.Category;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CategoryService {
    Flux<Category> findAll();
    Mono<Category> findCategoryById(String id);
}
