package com.wiselap.accounts.users.AddUsersPackage;

public class UpdateUserMethodModel {

    private Long accountingProfileId;
    private Long shopAgentId;
    private String userName;
    private String userProfile;
    private String userId;

    public UpdateUserMethodModel( Long accountingProfileId, Long shopAgentId, String userName, String userProfile,String userId) {
        this.accountingProfileId = accountingProfileId;
        this.shopAgentId = shopAgentId;
        this.userName = userName;
        this.userProfile = userProfile;
        this.userId = userId;
    }

    public Long getAccountingProfileId() {
        return accountingProfileId;
    }

    public Long getShopAgentId() {
        return shopAgentId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserProfile() {
        return userProfile;
    }
}
