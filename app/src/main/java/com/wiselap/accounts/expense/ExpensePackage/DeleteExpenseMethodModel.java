package com.wiselap.accounts.expense.ExpensePackage;

import com.google.gson.annotations.SerializedName;

public class DeleteExpenseMethodModel {
    Long expenseId;

    public DeleteExpenseMethodModel(Long expenseId) {
        this.expenseId = expenseId;
    }

    public Long getExpenseId() {
        return expenseId;
    }
}