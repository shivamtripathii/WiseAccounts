package com.wiselap.accounts.users.UsersPackage;

public class DeleteUserMethodModel {
    Long accountingProfileId;
    Long shopAgentId;
    String userId;

    public DeleteUserMethodModel(Long accountingProfileId, Long shopAgentId, String userId) {
        this.accountingProfileId = accountingProfileId;
        this.shopAgentId = shopAgentId;
        this.userId = userId;
    }


    public Long getAccountingProfileId() {
        return accountingProfileId;
    }

    public Long getShopAgentId() {
        return shopAgentId;
    }

    public String getUserId() {
        return userId;
    }
}