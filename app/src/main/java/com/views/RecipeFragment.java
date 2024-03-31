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
import com.viewmodels.CookBookViewModel;
import com.viewmodels.LoginViewModel;
import com.viewmodels.PantryViewModel;
import com.viewmodels.UserViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private List<Recipe> cachedRecipes = new ArrayList<>();
    private Map<String, Ingredient> cachedPantryItems = new HashMap<>();


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
        editTextRecipeName = view.findViewById(R.id.editTextRecipeName);
        editTextIngredients = view.findViewById(R.id.editTextIngredients);


        /**
         * TODO 1: Bind the scrollable list of recipes here
         * Then actually create the scrollable list somewhere else
         */
        List<Recipe> recipes = cookBook.getUserRecipes();
        recipesRecyclerView = view.findViewById(R.id.recipesRecyclerView);
        recipesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Map<String, Ingredient> pantryItems = new HashMap<>();

        recipeAdapter = new RecipeAdapter(new ArrayList<>(), pantryItems, getContext(),
                recipe -> openRecipeDetails(recipe));
        recipesRecyclerView.setAdapter(recipeAdapter);
        setupSortAndFilterButtons(view);

        /**
         * TODO 2: Implement addRecipe function in CookBookViewModel to parse the data
         * and add it to the database
         */
        submitRecipeButton.setOnClickListener(v -> {
            cookBook.addRecipe(getContext(), editTextRecipeName, editTextIngredients);
            editTextRecipeName.setText("");
            editTextIngredients.setText("");
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



    private void setupSortAndFilterButtons(View view) {
        Button sortButton = view.findViewById(R.id.sortButton);
        Button filterButton = view.findViewById(R.id.filterButton);

        sortButton.setOnClickListener(v -> applySortStrategy());
        filterButton.setOnClickListener(v -> applyFilterStrategy());
    }

    private void applySortStrategy() {
        List<Recipe> sortedRecipes = new ArrayList<>(cachedRecipes);
        sortedRecipes.sort((recipe1, recipe2) ->
                recipe1.getName().compareToIgnoreCase(recipe2.getName()));
        updateRecipeList(sortedRecipes);
    }

    private void applyFilterStrategy() {
        List<Recipe> filteredRecipes = new ArrayList<>();
        for (Recipe recipe : cachedRecipes) {
            boolean allIngredientsAvailable = true;
            for (Ingredient ingredient : recipe.getIngredients()) {
                Ingredient pantryIngredient = cachedPantryItems.get(ingredient.getName());
                if (pantryIngredient == null || Integer.parseInt(pantryIngredient.getQuantity())
                        < Integer.parseInt(ingredient.getQuantity())) {
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

    private void updateRecipeList(List<Recipe> recipes) {
        if (recipeAdapter == null) {
            recipeAdapter = new RecipeAdapter(recipes, cachedPantryItems, getContext(),
                    this::openRecipeDetails);
            recipesRecyclerView.setAdapter(recipeAdapter);
        } else {
            recipeAdapter.updateRecipes(recipes);
        }
    }

    private void openRecipeDetails(Recipe recipe) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(recipe.getName());
        builder.setPositiveButton("Close", (dialog, which) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}