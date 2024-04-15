package com.views;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.model.StrategySprint4.Recipe;
import com.model.ShoppingItem;
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
    private Button goShoppingButton;
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
        goShoppingButton = view.findViewById(R.id.buttonGoShopping);

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

        goShoppingButton.setOnClickListener(v -> {
            String quantityNeeded = "";
            int currentQuantity = 0;
            ArrayList<Recipe> checkedItems = recipeAdapter.getCheckedItems();
            for (int i = 0; i < checkedItems.size(); i++) {
                Recipe recipe = checkedItems.get(i);
                for (int j = 0; j < recipe.getIngredients().size(); j++) {
                    Ingredient ingredient = recipe.getIngredients().get(j);
                    int pantryIndex = userViewModel.getUser().locateIngredient(ingredient);
                    if (pantryIndex == -1) {
                        currentQuantity = 0;
                    } else {
                        currentQuantity = Integer.parseInt(userViewModel.getUser().getPantry()
                                .get(pantryIndex).getQuantity());
                    }
                    int quantityRequired = Integer.parseInt(ingredient.getQuantity());
                    if (currentQuantity < quantityRequired) {
                        quantityNeeded = Integer.toString(quantityRequired - currentQuantity);
                    }
                    userViewModel.addShoppingItem(ingredient.getName(),
                            quantityNeeded, "100");
                }
            }
            generateNewAdapter();


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
        StringBuilder message = new StringBuilder("Total Calories: " + recipe.getCalories()
                + "\n" + "Ingredients Required:\n");
        for (Ingredient ingredient : recipe.getIngredients()) {
            message.append("- ").append(ingredient.getQuantity()).append(" ")
                    .append(ingredient.getName()).append("\n");
        }
        builder.setMessage(message.toString());
        builder.setNegativeButton("Close", (dialog, which) -> dialog.dismiss());
        builder.setPositiveButton("Cook", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Call a function to remove ingredients from the user's account
                cookRecipe(recipe);

                // Dismiss the dialog
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void generateNewAdapter() {
        recipeAdapter = new RecipeAdapter(userViewModel.getUser().getCookBook(),
                userViewModel.getUser().getPantry(), getContext(),
                recipe -> openRecipeDetails(recipe));
        recipesRecyclerView.setAdapter(recipeAdapter);
    }
    private void cookRecipe(Recipe recipe) {
        ArrayList<Ingredient> usedIngredients = recipe.getIngredients();
        User user = userViewModel.getUser();
        ArrayList<Ingredient> pantry = user.getPantry();
        for (Ingredient recipeIngredient : usedIngredients) {
            // Check if the recipe ingredient exists in the user's pantry
            for (Ingredient pantryIngredient : pantry) {
                if (pantryIngredient.getName().equals(recipeIngredient.getName())) {
                    // Convert the quantity from String to int
                    int pantryQuantity = Integer.parseInt(pantryIngredient.getQuantity());
                    int recipeQuantity = Integer.parseInt(recipeIngredient.getQuantity());

                    // Decrement the quantity of the ingredient in the pantry
                    pantryQuantity -= recipeQuantity;

                    // If the quantity becomes zero, remove the ingredient from the pantry
                    if (pantryQuantity <= 0) {
                        pantry.remove(pantryIngredient);
                    } else {
                        // Update the quantity in the pantryIngredient object
                        pantryIngredient.setQuantity(String.valueOf(pantryQuantity));
                    }
                    break; // Exit the loop since the ingredient has been found and updated
                }
            }
        }
    }

}