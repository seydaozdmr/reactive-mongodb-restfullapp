package com.recipe.app.mongodb.controller;

import com.recipe.app.mongodb.model.Category;
import com.recipe.app.mongodb.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;


    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("")
    public ResponseEntity<List<Category>> getAll(){
        return ResponseEntity.ok(categoryService.findAll().collectList().block());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findCategoryById(@PathVariable String id){
        return ResponseEntity.ok(categoryService.findCategoryById(id).block());
    }
}
