package com.wiselap.accounts.expense.AddExpensePackage;

public class UpdateExpenseMethodModel {
    private String date;
    private String expenseName;
    private Long expenseAmount;
    private String remarks;
    private Long loginId;
    private Long expenseTypeId;
    private Long shopAgentId;
    private Long expenseId;

    public UpdateExpenseMethodModel(String date, String expenseName, Long expenseAmount, String remarks, Long loginId, Long expenseTypeId, Long shopAgentId, Long expenseId) {
        this.date = date;
        this.expenseName = expenseName;
        this.expenseAmount = expenseAmount;
        this.remarks = remarks;
        this.loginId = loginId;
        this.expenseTypeId = expenseTypeId;
        this.shopAgentId = shopAgentId;
        this.expenseId = expenseId;
    }

    public String getDate() {
        return date;
    }

    public String getExpenseName() {
        return expenseName;
    }

    public Long getExpenseAmount() {
        return expenseAmount;
    }

    public String getRemarks() {
        return remarks;
    }

    public Long getLoginId() {
        return loginId;
    }

    public Long getExpenseTypeId() {
        return expenseTypeId;
    }

    public Long getShopAgentId() {
        return shopAgentId;
    }

    public Long getExpenseId() {
        return expenseId;
    }
}
