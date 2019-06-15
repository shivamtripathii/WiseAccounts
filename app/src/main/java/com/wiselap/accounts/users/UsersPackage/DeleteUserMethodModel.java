package com.wiselap.accounts.users.UsersPackage;

public class DeleteUserMethodModel {
    Long accountingUserId;
    String userId;

    public DeleteUserMethodModel(Long accountingUserId, String userId) {
        this.accountingUserId = accountingUserId;
        this.userId = userId;
    }

    public Long getAccountingUserId() {
        return accountingUserId;
    }

    public String getUserId() {
        return userId;
    }
}