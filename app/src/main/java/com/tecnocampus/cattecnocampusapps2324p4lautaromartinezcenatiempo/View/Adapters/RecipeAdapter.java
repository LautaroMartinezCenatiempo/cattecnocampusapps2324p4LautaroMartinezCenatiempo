package com.tecnocampus.cattecnocampusapps2324p4lautaromartinezcenatiempo.View.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tecnocampus.cattecnocampusapps2324p4lautaromartinezcenatiempo.R;
import com.tecnocampus.cattecnocampusapps2324p4lautaromartinezcenatiempo.View.Activities.RecipeDetailActivity;
import com.tecnocampus.cattecnocampusapps2324p4lautaromartinezcenatiempo.model.Recipe;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private Context context;
    private List<Recipe> recipeList;

    public RecipeAdapter(Context context, List<Recipe> recipeList) {
        this.context = context;
        this.recipeList = recipeList;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recipe, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        Recipe recipe = recipeList.get(position);
        holder.bind(recipe);
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView nameTextView;

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.recipe_image);
            nameTextView = itemView.findViewById(R.id.recipe_name);
        }

        public void bind(Recipe recipe) {
            nameTextView.setText(recipe.getName());
            // Load image using Glide
            Glide.with(context)
                    .load(recipe.getImageUrl())
                    .placeholder(R.drawable.errorimage)
                    .error(R.drawable.defaultimage)
                    .into(imageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Create an Intent to open RecipeDetailActivity
                    Intent intent = new Intent(context, RecipeDetailActivity.class);
                    intent.putExtra("recipe_id", recipe.getId());
                    intent.putExtra("recipe_name", recipe.getName());
                    context.startActivity(intent);
                }
            });
        }
    }
}
