package com.views;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.model.Ingredient;
import com.model.Recipe;
import com.model.Strategy.RecipeContext;
import com.model.User;
import com.viewmodels.CookBookViewModel;
import com.viewmodels.LoginViewModel;
import com.viewmodels.PantryViewModel;
import com.viewmodels.UserViewModel;

import java.util.ArrayList;

public class RecipeFragment extends Fragment {
    private static LoginViewModel loginViewModel;
    private static UserViewModel userViewModel;
    private static CookBookViewModel cookBook;
    private static PantryViewModel pantry;
    private Button submitRecipeButton;
    private EditText editTextRecipeName;
    private EditText editTextIngredients;
    private HomeActivity currentContext;
    private View view;
    private RecyclerView recipesRecyclerView;
    private RecipeAdapter recipeAdapter;
    private RecipeContext recipeContext = new RecipeContext((recipes, pantry) -> recipes);
    private Button sortNameButton;
    private Button sortIngredientsButton;
    private Button cookRecipeButton;


    public RecipeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_recipe, container, false);
        //Components of Recipe page

        loginViewModel = LoginViewModel.getInstance();
        userViewModel = UserViewModel.getInstance();
        cookBook = CookBookViewModel.getInstance();
        pantry = PantryViewModel.getInstance();
        submitRecipeButton = view.findViewById(R.id.buttonSaveRecipe);
        sortNameButton = view.findViewById(R.id.sortButton);
        sortIngredientsButton = view.findViewById(R.id.filterButton);
        editTextRecipeName = view.findViewById(R.id.editTextRecipeName);
        editTextIngredients = view.findViewById(R.id.editTextIngredients);
//        cookRecipeButton = view.findViewById(R.id.cookRecipeButton);

        ArrayList<Recipe> recipes = userViewModel.getUser().getCookBook();
        recipesRecyclerView = view.findViewById(R.id.recipesRecyclerView);
        recipesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recipeAdapter = new RecipeAdapter(recipes, userViewModel.getUser()
                .getPantry(), getContext(), recipe -> openRecipeDetails(recipe));
        recipesRecyclerView.setAdapter(recipeAdapter);

        submitRecipeButton.setOnClickListener(v -> {
            cookBook.addRecipe(getContext(), editTextRecipeName, editTextIngredients);
            editTextRecipeName.setText("");
            editTextIngredients.setText("");
            ArrayList<Recipe> newRecipes = userViewModel.getUser().getCookBook();
            recipeAdapter = new RecipeAdapter(newRecipes, userViewModel.getUser()
                    .getPantry(), getContext(), recipe -> openRecipeDetails(recipe));
            recipesRecyclerView.setAdapter(recipeAdapter);
        });

        sortNameButton.setOnClickListener(v -> {
            applySortStrategy();
        });

        sortIngredientsButton.setOnClickListener(v -> {
            applyFilterStrategy();
        });
        return view;
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment RecipeFragment.
     */
    public static RecipeFragment newInstance() {
        RecipeFragment fragment = new RecipeFragment();
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public void setContext(HomeActivity context) {
        this.currentContext = context;
    }

    private void applySortStrategy() {
        userViewModel.getUser().getCookBook().sort((recipe1, recipe2) ->
                recipe1.getName().compareToIgnoreCase(recipe2.getName()));
        updateRecipeList(userViewModel.getUser().getCookBook());
    }

    private void applyFilterStrategy() {
        ArrayList<Recipe> filteredRecipes = new ArrayList<>();
        User user = userViewModel.getUser();
        for (Recipe recipe : user.getCookBook()) {
            boolean allIngredientsAvailable = true;
            for (Ingredient ingredient : recipe.getIngredients()) {
                int pantryIndex = user.locateIngredient(ingredient);
                if (pantryIndex != -1) {
                    Ingredient pantryIngredient = user.getPantry().get(pantryIndex);
                    if (Integer.parseInt(pantryIngredient.getQuantity())
                            < Integer.parseInt(ingredient.getQuantity())) {
                        allIngredientsAvailable = false;
                        break;
                    }
                } else {
                    allIngredientsAvailable = false;
                    break;
                }
            }
            if (allIngredientsAvailable) {
                filteredRecipes.add(recipe);
            }
        }
        updateRecipeList(filteredRecipes);
    }

    private void updateRecipeList(ArrayList<Recipe> recipes) {
        recipeAdapter = new RecipeAdapter(recipes, userViewModel.getUser()
                .getPantry(), getContext(), recipe -> openRecipeDetails(recipe));
        recipesRecyclerView.setAdapter(recipeAdapter);
    }

    private void openRecipeDetails(Recipe recipe) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(recipe.getName());
        builder.setMessage("Total Calories: " + recipe.getCalories());
        builder.setPositiveButton("Close", (dialog, which) -> dialog.dismiss());
        builder.setNegativeButton("Cook", (dialog, which) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}