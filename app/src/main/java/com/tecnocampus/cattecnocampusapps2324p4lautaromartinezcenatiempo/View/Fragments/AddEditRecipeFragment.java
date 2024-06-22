package com.tecnocampus.cattecnocampusapps2324p4lautaromartinezcenatiempo.View.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.tecnocampus.cattecnocampusapps2324p4lautaromartinezcenatiempo.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddEditRecipeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddEditRecipeFragment extends Fragment {

    private static final String RECIPE_ID = "recipe_id";
    private static final String RECIPE_NAME = "recipe_name";

    private String mRecipeId;
    private String mRecipeName;

    public AddEditRecipeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddEditRecipeFragment.
     */
    public static AddEditRecipeFragment newInstance(String param1, String param2) {
        AddEditRecipeFragment fragment = new AddEditRecipeFragment();
        Bundle args = new Bundle();
        args.putString(RECIPE_ID, param1);
        args.putString(RECIPE_NAME, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mRecipeId = getArguments().getString(RECIPE_ID);
            mRecipeName = getArguments().getString(RECIPE_NAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_edit_recipe, container, false);
    }
}