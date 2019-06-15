package com.wiselap.accounts.model;

import com.google.gson.annotations.SerializedName;

public class AuthenticationUsingEmail {

    @SerializedName("uniqueIdentityField")
    private String email;

    public AuthenticationUsingEmail(String email) {
        this.email=email;
    }

    public String getEmail() {
        return email;
    }
}
