package com.viewmodels;

public class LoginViewModel {
    private static LoginViewModel instance;

    public static synchronized LoginViewModel getInstance() {
        if (instance == null) {
            instance = new LoginViewModel();
        }
        return instance;
    }
}
