package com.wiselap.accounts.users.UsersPackage;

public class DeleteUserMethodModel {
    Long accountingUserId;
    Long shopAgentId;
    String userId;

    public DeleteUserMethodModel(Long accountingUserId, Long shopAgentId, String userId) {
        this.accountingUserId = accountingUserId;
        this.shopAgentId = shopAgentId;
        this.userId = userId;
    }


    public Long getAccountingUserId() {
        return accountingUserId;
    }

    public Long getShopAgentId() {
        return shopAgentId;
    }

    public String getUserId() {
        return userId;
    }
}