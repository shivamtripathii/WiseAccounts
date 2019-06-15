package com.wiselap.accounts.expense.AddExpensePackage;

public class AddExpenseReturnModel {
    private Long expenseId;
    private String date;
    private String expense_name;
    private Long expense_amount;
    private String remarks;
    private Long accountingProfileId;
    private String imagePath;
    private String localDateTime;
    private Long expenseTypeId;
    private Long shopAgentId;

    public AddExpenseReturnModel(Long expenseId, String date, String expense_name, Long expense_amount, String remarks, Long accountingProfileId, String imagePath, String localDateTime, Long expenseTypeId, Long shopAgentId) {
        this.expenseId = expenseId;
        this.date = date;
        this.expense_name = expense_name;
        this.expense_amount = expense_amount;
        this.remarks = remarks;
        this.accountingProfileId = accountingProfileId;
        this.imagePath = imagePath;
        this.localDateTime = localDateTime;
        this.expenseTypeId = expenseTypeId;
        this.shopAgentId = shopAgentId;
    }

    public Long getExpenseId() {
        return expenseId;
    }

    public String getDate() {
        return date;
    }

    public String getExpense_name() {
        return expense_name;
    }

    public Long getExpense_amount() {
        return expense_amount;
    }

    public String getRemarks() {
        return remarks;
    }

    public Long getAccountingProfileId() {
        return accountingProfileId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getLocalDateTime() {
        return localDateTime;
    }

    public Long getExpenseTypeId() {
        return expenseTypeId;
    }

    public Long getShopAgentId() {
        return shopAgentId;
    }


}