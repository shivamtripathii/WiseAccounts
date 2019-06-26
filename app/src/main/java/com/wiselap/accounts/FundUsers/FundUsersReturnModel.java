package com.wiselap.accounts.FundUsers;

import java.io.Serializable;

public class FundUsersReturnModel implements Serializable {

    private Long advance;
    private String shopAgentName;
    private Long shopAgentId;

    public FundUsersReturnModel(Long advance, String shopAgentName, Long shopAgentId) {
        this.advance = advance;
        this.shopAgentName = shopAgentName;
        this.shopAgentId = shopAgentId;
    }

    public Long getAdvance() {
        return advance;
    }

    public String getShopAgentName() {
        return shopAgentName;
    }

    public Long getShopAgentId() {
        return shopAgentId;
    }
}
