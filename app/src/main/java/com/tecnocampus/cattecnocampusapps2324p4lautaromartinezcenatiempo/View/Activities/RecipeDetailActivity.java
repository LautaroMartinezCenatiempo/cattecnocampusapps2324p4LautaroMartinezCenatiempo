package com.tecnocampus.cattecnocampusapps2324p4lautaromartinezcenatiempo.View.Activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.tecnocampus.cattecnocampusapps2324p4lautaromartinezcenatiempo.R;
import com.tecnocampus.cattecnocampusapps2324p4lautaromartinezcenatiempo.model.Recipe;
import com.tecnocampus.cattecnocampusapps2324p4lautaromartinezcenatiempo.viewModel.RecipeViewModel;

public class RecipeDetailActivity extends AppCompatActivity {

    private ImageView recipeImageView;
    private TextView recipeNameTextView;
    private TextView recipeDescriptionTextView;
    private TextView recipeIngredientsTextView;
    private TextView recipeStepsTextView;
    private RecipeViewModel recipeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        // Obtener referencias de las vistas
        recipeImageView = findViewById(R.id.recipe_image);
        recipeNameTextView = findViewById(R.id.recipe_name);
        recipeDescriptionTextView = findViewById(R.id.recipe_description);
        recipeIngredientsTextView = findViewById(R.id.recipe_ingredients);
        recipeStepsTextView = findViewById(R.id.recipe_steps);

        // Obtener el ID de la receta desde el Intent
        String recipeId = getIntent().getStringExtra("recipe_id");

        // Inicializar ViewModel con ViewModelProvider
        recipeViewModel = new ViewModelProvider(this).get(RecipeViewModel.class);

        // Obtener los detalles de la receta y mostrarlos
        recipeViewModel.getRecipeById(recipeId).observe(this, recipe -> {
            if (recipe != null) {
                displayRecipeDetails(recipe);
            }
        });
    }

    private void displayRecipeDetails(Recipe recipe) {
        recipeNameTextView.setText(recipe.getName());
        recipeDescriptionTextView.setText(recipe.getDescription());
        recipeIngredientsTextView.setText(recipe.getIngredients());
        recipeStepsTextView.setText(recipe.getSteps());

        Glide.with(this)
                .load(recipe.getImageUrl())
                .placeholder(R.drawable.errorimage)
                .error(R.drawable.defaultimage)
                .into(recipeImageView);
    }
}
