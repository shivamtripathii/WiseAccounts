package com.wiselap.accounts.FundTransferMethod;

public class BankModel {

    private Long shopAgentId;
    private Long amount;
    private Long transferredByShopAgentId;
    private String bankName;
    private Long bankAccountNo;
    private String bankIfscNo;
    private String remark;

    public BankModel(Long shopAgentId, Long amount, Long transferredByShopAgentId, String bankName, Long bankAccountNo, String bankIfscNo, String remark) {
        this.shopAgentId = shopAgentId;
        this.amount = amount;
        this.transferredByShopAgentId = transferredByShopAgentId;
        this.bankName = bankName;
        this.bankAccountNo = bankAccountNo;
        this.bankIfscNo = bankIfscNo;
        this.remark = remark;
    }

    public Long getShopAgentId() {
        return shopAgentId;
    }

    public Long getAmount() {
        return amount;
    }

    public Long getTransferredByShopAgentId() {
        return transferredByShopAgentId;
    }

    public String getBankName() {
        return bankName;
    }

    public Long getBankAccountNo() {
        return bankAccountNo;
    }

    public String getBankIfscNo() {
        return bankIfscNo;
    }

    public String getRemark() {
        return remark;
    }
}
