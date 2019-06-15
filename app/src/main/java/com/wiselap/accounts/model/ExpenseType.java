package com.wiselap.accounts.model;

import com.google.gson.annotations.SerializedName;
import com.wiselap.accounts.constants.AppConstants;

import java.io.Serializable;

public class ExpenseType implements Serializable {

    @SerializedName(AppConstants.expenseTypeId)
    private Integer id;

    @SerializedName(AppConstants.expenseType)
    private String expense_name;
    private String frequency;
    @SerializedName(AppConstants.defaultAmount)
    private Double amount;

    private Long accountingProfileId;

    public ExpenseType(Integer id, String expense_name, String frequency, Double amount) {
        this.expense_name = expense_name;
        this.id = id;
        this.frequency = frequency;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExpense_name() {
        return expense_name;
    }

    public void setExpense_name(String expense_name) {
        this.expense_name = expense_name;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getAccountingProfileId() {
        return accountingProfileId;
    }

    public void setAccountingProfileId(Long accountingProfileId) {
        this.accountingProfileId = accountingProfileId;
    }
}

