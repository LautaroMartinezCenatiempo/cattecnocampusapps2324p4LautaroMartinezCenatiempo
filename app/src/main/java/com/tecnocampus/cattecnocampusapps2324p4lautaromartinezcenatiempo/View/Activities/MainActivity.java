package com.tecnocampus.cattecnocampusapps2324p4lautaromartinezcenatiempo.View.Activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tecnocampus.cattecnocampusapps2324p4lautaromartinezcenatiempo.R;
import com.tecnocampus.cattecnocampusapps2324p4lautaromartinezcenatiempo.View.Adapters.RecipeAdapter;
import com.tecnocampus.cattecnocampusapps2324p4lautaromartinezcenatiempo.View.Utils.Callback;
import com.tecnocampus.cattecnocampusapps2324p4lautaromartinezcenatiempo.model.Recipe;
import com.tecnocampus.cattecnocampusapps2324p4lautaromartinezcenatiempo.model.RecipeRepository;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private RecipeRepository recipeRepository;
    private RecyclerView recyclerView;
    private RecipeAdapter recipeAdapter;
    private List<Recipe> recipeList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recipeRepository = new RecipeRepository(this);

        recyclerView = findViewById(R.id.recyclerView);
        if (getResources().getConfiguration().orientation == android.content.res.Configuration.ORIENTATION_PORTRAIT) {
            // Vertical orientation
            LinearLayoutManager layoutManagerVertical = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManagerVertical);
        } else {
            // Horizontal orientation
            LinearLayoutManager layoutManagerHorizontal = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            recyclerView.setLayoutManager(layoutManagerHorizontal);
        }


        recipeList = new ArrayList<>();
        recipeAdapter = new RecipeAdapter(this, recipeList);
        recyclerView.setAdapter(recipeAdapter);

        //createExampleRecipes();

        // Cargar todas las recetas al iniciar la actividad
        loadAllRecipes();
    }

    private void loadAllRecipes() {
        recipeRepository.getAllRecipes(new Callback<List<Recipe>>() {
            @Override
            public void onSuccess(List<Recipe> result) {
                recipeList.clear();
                recipeList.addAll(result);
                recipeAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Exception e) {
                Log.e(TAG, "Error fetching recipes"+e.getMessage(), e);
                Toast.makeText(MainActivity.this, "Failed to fetch recipes: "+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Si deseas cargar una receta específica por ID
    private void loadRecipeById(String recipeId) {
        recipeRepository.getRecipeById(recipeId, new Callback<Recipe>() {
            @Override
            public void onSuccess(Recipe result) {
                if (result != null) {
                    // Manejar la receta obtenida
                } else {
                    Toast.makeText(MainActivity.this, "Recipe not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Exception e) {
                Log.e(TAG, "Error fetching recipe by ID", e);
                Toast.makeText(MainActivity.this, "Failed to fetch recipe", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void createExampleRecipes() {
        for (int i = 1; i <= 10; i++) {
            Recipe recipe = new Recipe();
            recipe.setId("recipe_" + i);
            recipe.setName("Recipe " + i);
            recipe.setDescription("Description of Recipe " + i);
            recipe.setIngredients("Ingredient 1, Ingredient 2, Ingredient 3");
            recipe.setSteps("Step 1, Step 2, Step 3");

            int finalI = i;
            recipeRepository.addRecipe(recipe, new Callback<Void>() {
                @Override
                public void onSuccess(Void result) {
                    Log.d("MainActivity", "Recipe " + finalI + " added successfully");
                    // No mostramos un Toast por cada receta para no saturar la pantalla
                }

                @Override
                public void onFailure(Exception e) {
                    Log.e("MainActivity", "Failed to add Recipe " + finalI, e);
                    // No mostramos un Toast por cada receta para no saturar la pantalla
                }
            });
        }
    }
}