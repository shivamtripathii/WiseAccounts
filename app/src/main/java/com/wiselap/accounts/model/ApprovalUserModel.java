package com.wiselap.accounts.model;

import java.io.Serializable;

public class ApprovalUserModel implements Serializable {

    private String userName;
    private Double expense;

    public ApprovalUserModel(String userName, Double expense) {
        this.userName = userName;
        this.expense = expense;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Double getExpense() {
        return expense;
    }

    public void setExpense(Double expense) {
        this.expense = expense;
    }
}
