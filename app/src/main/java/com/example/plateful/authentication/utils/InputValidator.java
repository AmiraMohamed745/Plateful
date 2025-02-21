package com.example.plateful.authentication.utils;

public class InputValidator {

    public static boolean checkUsernameNotEmpty(String username) {
        return !username.isEmpty();
    }

    public static boolean checkEmailNotEmpty(String email) {
        return !email.isEmpty();
    }

    public static boolean checkPasswordNotEmpty(String password) {
        return !password.isEmpty();
    }

    public static boolean checkConfirmedPasswordNotEmpty(String confirmedPassword) {
        return !confirmedPassword.isEmpty();
    }

}
