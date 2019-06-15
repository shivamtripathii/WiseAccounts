package com.wiselap.accounts.expense.AddExpensePackage;

public class AddExpenseMethodModel {
    private String date;
    private String expense_name;
    private Long expense_amount;
    private String remarks;
    private Long loginId;
    private Long expenseTypeId;
    private Long accountingProfileId;



    public AddExpenseMethodModel(String date, String expense_name, Long expense_amount, String remarks, Long loginId, Long expenseTypeId, Long accountingProfileId) {
        this.date = date;
        this.expense_name = expense_name;
        this.expense_amount = expense_amount;
        this.remarks = remarks;
        this.loginId = loginId;
        this.expenseTypeId = expenseTypeId;
        this.accountingProfileId = accountingProfileId;
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

    public Long getLoginId() {
        return loginId;
    }

    public Long getExpenseTypeId() {
        return expenseTypeId;
    }

    public Long getAccountingProfileId() {
        return accountingProfileId;
    }
}

