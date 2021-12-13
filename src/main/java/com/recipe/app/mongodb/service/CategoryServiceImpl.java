package com.recipe.app.mongodb.service;

import com.recipe.app.mongodb.model.Category;
import com.recipe.app.mongodb.repository.CategoryRepository;
import com.recipe.app.mongodb.repository.reaktive.CategoryReactiveRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryReactiveRepository categoryRepository;

    public CategoryServiceImpl(CategoryReactiveRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Flux<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Mono<Category> findCategoryById(String id) {
        return categoryRepository.findById(id);
    }
}
