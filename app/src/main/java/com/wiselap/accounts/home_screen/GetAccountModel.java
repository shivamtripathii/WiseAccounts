package com.wiselap.accounts.home_screen;

public class GetAccountModel {

    private Long shopAgentId;

    public GetAccountModel(Long shopAgentId) {
        this.shopAgentId = shopAgentId;
    }

    public Long getShopAgentId() {
        return shopAgentId;
    }

    public void setShopAgentId(Long shopAgentId) {
        this.shopAgentId = shopAgentId;
    }
}
