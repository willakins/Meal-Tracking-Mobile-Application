package com.viewmodels;

import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.model.User;

public class CookBookViewModel {
    private static User user;
    private static LoginViewModel loginViewModel;
    private static CookBookViewModel instance;
    private static DatabaseReference mDatabase;

    /**
     * Singleton Constructor
     * @return the userViewModel instance
     */
    public static synchronized CookBookViewModel getInstance() {
        if (instance == null) {
            instance = new CookBookViewModel();
            loginViewModel = LoginViewModel.getInstance();
            mDatabase = loginViewModel.getmDatabase();
            user = loginViewModel.getUser();
        }
        return instance;
    }

    /**
     * TODO: Implement method and store the recipe in database.
     * Maybe need to consider how the scrollable list is updated at the end of this function call
     *
     * @param name the name of the recipe still in EditText format
     * @param ingredients the list of ingredients still in EditText format
     */
    public void addRecipe(EditText name, EditText ingredients) {

    }
}
