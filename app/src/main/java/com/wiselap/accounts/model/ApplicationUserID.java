package com.wiselap.accounts.model;


import com.google.gson.annotations.SerializedName;

public class ApplicationUserID {
    @SerializedName("applicationUserId")
    private long applicationUserId;

    public ApplicationUserID(long applicationUserId) {
        this.applicationUserId = applicationUserId;
    }

    public long getApplicationUserId() {
        return applicationUserId;
    }
}