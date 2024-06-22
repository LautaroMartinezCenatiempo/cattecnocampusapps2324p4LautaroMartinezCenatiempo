package com.tecnocampus.cattecnocampusapps2324p4lautaromartinezcenatiempo.model;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.tecnocampus.cattecnocampusapps2324p4lautaromartinezcenatiempo.View.Utils.Callback;
import com.tecnocampus.cattecnocampusapps2324p4lautaromartinezcenatiempo.model.Recipe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RecipeRepository {
    private FirebaseFirestore db;
    private RequestQueue requestQueue;
    private Context context;

    public RecipeRepository(Context context) {
        this.context = context;
        db = FirebaseFirestore.getInstance();
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    // Método para obtener una receta por su ID usando FirebaseFirestore
    public void getRecipeById(String recipeId, final Callback<Recipe> callback) {
        db.collection("recipes").document(recipeId)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            Recipe recipe = document.toObject(Recipe.class);
                            callback.onSuccess(recipe);
                        } else {
                            callback.onFailure(new Exception("Recipe not found"));
                        }
                    } else {
                        callback.onFailure(task.getException());
                    }
                });
    }

    // Método para obtener todas las recetas usando FirebaseFirestore
    public void getAllRecipes(final Callback<List<Recipe>> callback) {
        db.collection("recipes")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<Recipe> recipes = new ArrayList<>();
                        for (DocumentSnapshot document : task.getResult()) {
                            Recipe recipe = document.toObject(Recipe.class);
                            recipes.add(recipe);
                        }
                        callback.onSuccess(recipes);
                    } else {
                        callback.onFailure(task.getException());
                    }
                });
    }

    // Método para agregar una receta usando FirebaseFirestore
    public void addRecipe(Recipe recipe, final Callback<Void> callback) {
        db.collection("recipes").document(recipe.getId())
                .set(recipe)
                .addOnSuccessListener(aVoid -> callback.onSuccess(null))
                .addOnFailureListener(e -> callback.onFailure(e));
    }

    // Método para actualizar una receta usando FirebaseFirestore
    public void updateRecipe(String recipeId, Recipe recipe, final Callback<Void> callback) {
        db.collection("recipes").document(recipeId)
                .set(recipe)
                .addOnSuccessListener(aVoid -> callback.onSuccess(null))
                .addOnFailureListener(e -> callback.onFailure(e));
    }

    // Método para eliminar una receta usando FirebaseFirestore
    public void deleteRecipe(String recipeId, final Callback<Void> callback) {
        db.collection("recipes").document(recipeId)
                .delete()
                .addOnSuccessListener(aVoid -> callback.onSuccess(null))
                .addOnFailureListener(e -> callback.onFailure(e));
    }

    // Método para parsear un JSON Array de recetas a una lista de objetos Recipe
    private List<Recipe> parseRecipesFromJsonArray(JSONArray jsonArray) {
        List<Recipe> recipes = new ArrayList<>();
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Recipe recipe = new Recipe();
                recipe.setId(jsonObject.getString("id")); // Ajusta según la estructura del JSON
                recipe.setName(jsonObject.getString("name")); // Ajusta según la estructura del JSON
                // Más campos...
                recipes.add(recipe);
            }
        } catch (JSONException e) {
            Log.e("RecipeRepository", "Error parsing JSON", e);
        }
        return recipes;
    }
}
