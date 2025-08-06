package com.project1_example.Controller;

public class SessionManager {
 
    private static String currentUserEmail;

    public static void setCurrentUserEmail(String email) {
        currentUserEmail = email;
    }

    public static String getCurrentUserEmail() {
        return currentUserEmail;
    }

    public static boolean isLoggedIn() {
        return currentUserEmail != null && !currentUserEmail.isEmpty();
    }
}


