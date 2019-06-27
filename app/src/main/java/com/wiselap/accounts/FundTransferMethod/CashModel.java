package com.wiselap.accounts.FundTransferMethod;

public class CashModel {

    private Long shopAgentId;
    private Long amount;
    private String paymentMode;
    private Long transferredByShopAgentId;
    private String remark;

    public CashModel(Long shopAgentId, Long amount, String paymentMode, Long transferredByShopAgentId, String remark) {
        this.shopAgentId = shopAgentId;
        this.amount = amount;
        this.paymentMode = paymentMode;
        this.transferredByShopAgentId = transferredByShopAgentId;
        this.remark = remark;
    }

    public Long getShopAgentId() {
        return shopAgentId;
    }

    public Long getAmount() {
        return amount;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public Long getTransferredByShopAgentId() {
        return transferredByShopAgentId;
    }

    public String getRemark() {
        return remark;
    }
}
