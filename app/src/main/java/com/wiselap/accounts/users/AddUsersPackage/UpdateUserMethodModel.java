package com.wiselap.accounts.users.AddUsersPackage;

public class UpdateUserMethodModel {


    private Long accountingUserId;
    private Long accountingProfileId;
    private Long shopAgentId;
    private String userName;
    private String userId;
    private String userProfile;

    public UpdateUserMethodModel(Long accountingUserId, Long accountingProfileId, Long shopAgentId, String userName, String userId, String userProfile) {
        this.accountingUserId = accountingUserId;
        this.accountingProfileId = accountingProfileId;
        this.shopAgentId = shopAgentId;
        this.userName = userName;
        this.userId = userId;
        this.userProfile = userProfile;
    }

    public Long getAccountingUserId() {
        return accountingUserId;
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
