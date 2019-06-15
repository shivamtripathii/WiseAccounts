package com.wiselap.accounts.expense.ExpensePackage;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ExpenseReturnModel implements Serializable {
    @SerializedName("Expensename")
    String Expense_name;
    String Remarks;
    String Date;
    Long expenseId;
    Long Expense_amount;
    Long ExpenseTypeId;

    public ExpenseReturnModel(String expense_name, String remarks, String date, Long expenseId, Long expense_amount, Long expenseTypeId) {
        Expense_name = expense_name;
        Remarks = remarks;
        Date = date;
        this.expenseId = expenseId;
        Expense_amount = expense_amount;
        ExpenseTypeId = expenseTypeId;
    }

    public String getExpense_name() {
        return Expense_name;
    }

    public String getRemarks() {
        return Remarks;
    }

    public String getDate() {
        return Date;
    }

    public Long getExpenseId() {
        return expenseId;
    }

    public Long getExpense_amount() {
        return Expense_amount;
    }

    public Long getExpenseTypeId() {
        return ExpenseTypeId;
    }
}
