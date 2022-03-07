package com.devoteam.demoassignments.recipemanager.controller.dto;

import java.util.Set;

import javax.validation.constraints.*;

public class AddUserDTO {
    @NotBlank
    @Size(min = 2, max = 20)
    private String username;
  
    @NotBlank
    @Size(min = 6, max = 20)
    private String password;
  
    @NotBlank
    @Size(max = 60)
    @Email
    private String email;
  
    private Set<String> role;

    /**
     * @return String return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return String return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return String return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return Set<String> return the role
     */
    public Set<String> getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(Set<String> role) {
        this.role = role;
    }

}