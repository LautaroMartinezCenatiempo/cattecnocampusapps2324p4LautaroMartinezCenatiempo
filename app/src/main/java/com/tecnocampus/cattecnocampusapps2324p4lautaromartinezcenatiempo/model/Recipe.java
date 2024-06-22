package com.tecnocampus.cattecnocampusapps2324p4lautaromartinezcenatiempo.model;

public class Recipe {
    private String id;
    private String name;
    private String description;
    private String imageUrl;
    private String ingredients;
    private String steps;

    // Constructor, getters y setters
    public Recipe() {}
    public Recipe(String id) {
        this.id = id;
    }
    public Recipe(String id, String name, String description, String imageUrl, String ingredients, String steps) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.ingredients = ingredients;
        this.steps = steps;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }
}

