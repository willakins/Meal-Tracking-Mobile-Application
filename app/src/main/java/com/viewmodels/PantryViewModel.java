package com.viewmodels;

import com.google.firebase.database.DatabaseReference;
import com.model.User;

public class PantryViewModel {
    private static User user;
    private static LoginViewModel loginViewModel;
    private static PantryViewModel instance;
    private static DatabaseReference mDatabase;

    /**
     * Singleton Constructor
     * @return the userViewModel instance
     */
    public static synchronized PantryViewModel getInstance() {
        if (instance == null) {
            instance = new PantryViewModel();
            loginViewModel = LoginViewModel.getInstance();
            mDatabase = loginViewModel.getmDatabase();
            user = loginViewModel.getUser();
        }
        return instance;
    }


}
