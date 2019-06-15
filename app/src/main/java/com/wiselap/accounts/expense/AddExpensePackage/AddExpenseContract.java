package com.wiselap.accounts.expense.AddExpensePackage;

import com.wiselap.accounts.interfaces.BaseView;

import java.util.ArrayList;

public interface AddExpenseContract {
    interface view extends BaseView {
        void sendExpenseType(ArrayList<String> expenseTypes);
        void updateExpense();

    }
    interface presenter{
        void addExpense(AddExpenseMethodModel addExpenseMethodModel);
        long getLoginId();
        long getApplicationUserID();
        void setAdapter();
        void updateExpense(UpdateExpenseMethodModel model);
        long checkExpenseTypeID(String expenseType);

    }
}
