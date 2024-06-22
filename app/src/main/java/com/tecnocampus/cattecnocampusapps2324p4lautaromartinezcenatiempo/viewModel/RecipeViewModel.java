package com.tecnocampus.cattecnocampusapps2324p4lautaromartinezcenatiempo.viewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.tecnocampus.cattecnocampusapps2324p4lautaromartinezcenatiempo.model.Recipe;
import com.tecnocampus.cattecnocampusapps2324p4lautaromartinezcenatiempo.model.RecipeRepository;
import com.tecnocampus.cattecnocampusapps2324p4lautaromartinezcenatiempo.View.Utils.Callback;

import java.util.List;

public class RecipeViewModel extends ViewModel {

    private RecipeRepository recipeRepository;

    public RecipeViewModel() {
        // Constructor vacío
    }

    public RecipeViewModel(Context context) {
        recipeRepository = new RecipeRepository(context);
    }

    public LiveData<Recipe> getRecipeById(String recipeId) {
        MutableLiveData<Recipe> recipeLiveData = new MutableLiveData<>();
        recipeRepository.getRecipeById(recipeId, new Callback<Recipe>() {
            @Override
            public void onSuccess(Recipe result) {
                recipeLiveData.setValue(result);
            }

            @Override
            public void onFailure(Exception e) {
                // Manejar errores si es necesario
            }
        });
        return recipeLiveData;
    }

    public LiveData<List<Recipe>> getAllRecipes() {
        MutableLiveData<List<Recipe>> recipesLiveData = new MutableLiveData<>();
        recipeRepository.getAllRecipes(new Callback<List<Recipe>>() {
            @Override
            public void onSuccess(List<Recipe> result) {
                recipesLiveData.setValue(result);
            }

            @Override
            public void onFailure(Exception e) {
                // Manejar errores si es necesario
            }
        });
        return recipesLiveData;
    }

    public void addRecipe(Recipe recipe, Callback<Void> callback) {
        recipeRepository.addRecipe(recipe, new Callback<Void>() {
            @Override
            public void onSuccess(Void result) {
                callback.onSuccess(result);
            }

            @Override
            public void onFailure(Exception e) {
                callback.onFailure(e);
            }
        });
    }

    public void updateRecipe(String recipeId, Recipe recipe, Callback<Void> callback) {
        recipeRepository.updateRecipe(recipeId, recipe, new Callback<Void>() {
            @Override
            public void onSuccess(Void result) {
                callback.onSuccess(result);
            }

            @Override
            public void onFailure(Exception e) {
                callback.onFailure(e);
            }
        });
    }

    public void deleteRecipe(String recipeId, Callback<Void> callback) {
        recipeRepository.deleteRecipe(recipeId, new Callback<Void>() {
            @Override
            public void onSuccess(Void result) {
                callback.onSuccess(result);
            }

            @Override
            public void onFailure(Exception e) {
                callback.onFailure(e);
            }
        });
    }
}
