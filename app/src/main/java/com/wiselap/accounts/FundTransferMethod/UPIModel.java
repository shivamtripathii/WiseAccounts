package com.wiselap.accounts.FundTransferMethod;

public class UPIModel {

    private Long shopAgentId;
    private Long amount;
    private String paymentMode;
    private Long transferredByShopAgentId;
    private String upiId;
    private String upiHolderName;
    private String transactionId;
    private String paymentGateway;
    private String remark;

    public UPIModel(Long shopAgentId, Long amount, String paymentMode, Long transferredByShopAgentId, String upiId, String upiHolderName, String transactionId, String paymentGateway, String remark) {
        this.shopAgentId = shopAgentId;
        this.amount = amount;
        this.paymentMode = paymentMode;
        this.transferredByShopAgentId = transferredByShopAgentId;
        this.upiId = upiId;
        this.upiHolderName = upiHolderName;
        this.transactionId = transactionId;
        this.paymentGateway = paymentGateway;
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

    public String getUpiId() {
        return upiId;
    }

    public String getUpiHolderName() {
        return upiHolderName;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getPaymentGateway() {
        return paymentGateway;
    }

    public String getRemark() {
        return remark;
    }
}
