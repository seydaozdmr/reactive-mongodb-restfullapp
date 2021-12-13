package com.recipe.app.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public class Category {
    @Id
    private String id;
    private String description;
    //@DBRef
    private List<Recipe> recipes=new ArrayList<>();

    public Category() {
    }

    public Category( String description, List<Recipe> recipes) {
        this.description = description;
        this.recipes = recipes;
    }

    public Category(String id, String description, List<Recipe> recipes) {
        this.id = id;
        this.description = description;
        this.recipes = recipes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }
}
