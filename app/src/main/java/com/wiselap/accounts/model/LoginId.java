package com.wiselap.accounts.model;


import com.google.gson.annotations.SerializedName;

public class LoginId {
    @SerializedName("loginId")
    private long loginId;

    public LoginId(long loginId) {
        this.loginId = loginId;
    }

    public long getLoginId() {
        return loginId;
    }
}
