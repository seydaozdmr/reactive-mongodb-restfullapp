package com.recipe.app.mongodb.repository;

import com.recipe.app.mongodb.model.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category,String> {
    Optional<Category> findByDescription(String description);
    List<Category> findAll();
}
