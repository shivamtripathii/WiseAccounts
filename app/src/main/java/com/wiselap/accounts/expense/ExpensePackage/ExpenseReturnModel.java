package com.wiselap.accounts.expense.ExpensePackage;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ExpenseReturnModel implements Serializable {
    private Long expenseId;
    private String statusChangeTime;
    private Long expenseTypeId;
    private String remarks;
    private Long balanceAmount;
    private Long expenseAmount;
    private String expenseName;
    private String date;
    private String currentStatus;
    private Long appDenyByShopAgentId;


    public ExpenseReturnModel(Long expenseId, String statusChangeTime, Long expenseTypeId, String remarks, Long balanceAmount, Long expenseAmount, String expenseName, String date, String currentStatus, Long appDenyByShopAgentId) {
        this.expenseId = expenseId;
        this.statusChangeTime = statusChangeTime;
        this.expenseTypeId = expenseTypeId;
        this.remarks = remarks;
        this.balanceAmount = balanceAmount;
        this.expenseAmount = expenseAmount;
        this.expenseName = expenseName;
        this.date = date;
        this.currentStatus = currentStatus;
        this.appDenyByShopAgentId = appDenyByShopAgentId;
    }

    public Long getExpenseId() {
        return expenseId;
    }

    public String getStatusChangeTime() {
        return statusChangeTime;
    }

    public Long getExpenseTypeId() {
        return expenseTypeId;
    }

    public String getRemarks() {
        return remarks;
    }

    public Long getBalanceAmount() {
        return balanceAmount;
    }

    public Long getExpenseAmount() {
        return expenseAmount;
    }

    public String getExpenseName() {
        return expenseName;
    }

    public String getDate() {
        return date;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public Long getAppDenyByShopAgentId() {
        return appDenyByShopAgentId;
    }
}
