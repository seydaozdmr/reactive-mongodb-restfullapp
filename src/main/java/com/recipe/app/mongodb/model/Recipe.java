package com.recipe.app.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public class Recipe {
    @Id
    private String id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;

    private Byte[] image;

    private Difficulty difficulty;

    private Notes notes;
    private List<Ingredient> ingredients=new ArrayList<>();

    //@DBRef
    private List<Category> categories=new ArrayList<>();

    public Recipe() {
    }

    public Recipe(String description, Integer prepTime, Integer cookTime, Integer servings, String source, String url, String directions, Byte[] image, Difficulty difficulty, Notes notes, List<Ingredient> ingredients, List<Category> categories) {
        this.description = description;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.servings = servings;
        this.source = source;
        this.url = url;
        this.directions = directions;
        this.image = image;
        this.difficulty = difficulty;
        this.notes = notes;
        this.ingredients = ingredients;
        this.categories = categories;
    }

    public Recipe(String id, String description, Integer prepTime, Integer cookTime, Integer servings, String source, String url, String directions, Byte[] image, Difficulty difficulty, Notes notes, List<Ingredient> ingredients, List<Category> categories) {
        this.id = id;
        this.description = description;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.servings = servings;
        this.source = source;
        this.url = url;
        this.directions = directions;
        this.image = image;
        this.difficulty = difficulty;
        this.notes = notes;
        this.ingredients = ingredients;
        this.categories = categories;
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

    public Integer getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(Integer prepTime) {
        this.prepTime = prepTime;
    }

    public Integer getCookTime() {
        return cookTime;
    }

    public void setCookTime(Integer cookTime) {
        this.cookTime = cookTime;
    }

    public Integer getServings() {
        return servings;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }

    public Notes getNotes() {
        return notes;
    }

    public void setNotes(Notes notes) {
        this.notes = notes;
        //notes.setRecipe(this);
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Recipe addIngredient(Ingredient ingredient){
        //ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return this;
    }

    public void updateRecipe(Recipe newRecipe){
        this.categories=newRecipe.getCategories();
        this.cookTime=newRecipe.getCookTime();
        this.description= newRecipe.getDescription();
        this.difficulty=newRecipe.getDifficulty();
        this.directions=newRecipe.getDirections();
        this.id= newRecipe.getId();
        this.image=newRecipe.getImage();
        this.ingredients=newRecipe.getIngredients();
        this.notes= newRecipe.getNotes();
        this.prepTime= newRecipe.getPrepTime();
        this.servings= newRecipe.getServings();
        this.source=newRecipe.getSource();
        this.url=newRecipe.getUrl();
    }
}
