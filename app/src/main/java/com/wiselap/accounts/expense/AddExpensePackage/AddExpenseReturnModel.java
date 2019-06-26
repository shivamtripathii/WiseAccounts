package com.wiselap.accounts.expense.AddExpensePackage;

public class AddExpenseReturnModel {
    private Long expenseId;
    private String date;
    private String expenseName;
    private Long expenseAmount;
    private String remarks;
    private String imagePath;
    private String localDateTime;
    private Long expenseTypeId;
    private Long shopAgentId;
    private String currentStatus;
    private Long balanceAmount;
    private Long appdenybyShopAgentId;
    private String statusChangeTime;

    public AddExpenseReturnModel(Long expenseId, String date, String expenseName, Long expenseAmount, String remarks, String imagePath, String localDateTime, Long expenseTypeId, Long shopAgentId, String currentStatus, Long balanceAmount, Long appdenybyShopAgentId, String statusChangeTime) {
        this.expenseId = expenseId;
        this.date = date;
        this.expenseName = expenseName;
        this.expenseAmount = expenseAmount;
        this.remarks = remarks;
        this.imagePath = imagePath;
        this.localDateTime = localDateTime;
        this.expenseTypeId = expenseTypeId;
        this.shopAgentId = shopAgentId;
        this.currentStatus = currentStatus;
        this.balanceAmount = balanceAmount;
        this.appdenybyShopAgentId = appdenybyShopAgentId;
        this.statusChangeTime = statusChangeTime;
    }

    public Long getExpenseId() {
        return expenseId;
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

    public String getCurrentStatus() {
        return currentStatus;
    }

    public Long getBalanceAmount() {
        return balanceAmount;
    }

    public Long getAppdenybyShopAgentId() {
        return appdenybyShopAgentId;
    }

    public String getStatusChangeTime() {
        return statusChangeTime;
    }
}