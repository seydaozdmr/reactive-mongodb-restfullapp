package com.recipe.app.mongodb.model;


public class Notes {

    private String id;
    private String recipeNotes;
    private Recipe recipe;

    public Notes() {
    }

    public Notes(String id, String recipeNotes, Recipe recipe) {
        this.id = id;
        this.recipeNotes = recipeNotes;
        this.recipe = recipe;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRecipeNotes() {
        return recipeNotes;
    }

    public void setRecipeNotes(String recipeNotes) {
        this.recipeNotes = recipeNotes;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
