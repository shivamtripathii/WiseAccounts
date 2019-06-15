package com.wiselap.accounts.users.UsersPackage;

public class UserMethodModel {

    Long accountingProfileId;
    Long shopAgentId;

    public UserMethodModel(Long accountingProfileId, Long shopAgentId) {
        this.accountingProfileId = accountingProfileId;
        this.shopAgentId = shopAgentId;
    }

    public Long getAccountingProfileId() {
        return accountingProfileId;
    }

    public Long getShopAgentId() {
        return shopAgentId;
    }
}
