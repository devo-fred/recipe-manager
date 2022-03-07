package com.devoteam.demoassignments.recipemanager.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public abstract class UserHelper {
    private static String username;

    public static String getCurrentUsername () {
        if (!isEmptyString(username)){
            return username;
        }

        if( SecurityContextHolder.getContext().getAuthentication() != null)  {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof UserDetails) {
                username = ((UserDetails)principal).getUsername();
            } else {
                username = principal.toString();
            }
        }
        return isEmptyString(username) ? "" : username;
    }

    private static boolean isEmptyString(String string) {
        return string == null || string.isEmpty();
    }

}