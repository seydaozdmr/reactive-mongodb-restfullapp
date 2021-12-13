package com.recipe.app.mongodb.controller;

import com.recipe.app.mongodb.model.UnitOfMeasure;
import com.recipe.app.mongodb.repository.reaktive.UnitOfMeasureReactiveRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UnitOfMeasuresController {
    private final UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;

    public UnitOfMeasuresController(UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository) {
        this.unitOfMeasureReactiveRepository = unitOfMeasureReactiveRepository;
    }

    @GetMapping("/unitofmeasures")
    public ResponseEntity<List<UnitOfMeasure>> getAll(){
        return ResponseEntity.ok(unitOfMeasureReactiveRepository.findAll().collectList().block());
    }
}
