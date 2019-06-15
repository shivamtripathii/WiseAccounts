package com.wiselap.accounts.ExpenseType;

import com.wiselap.accounts.interfaces.BaseView;
import com.wiselap.accounts.model.ExpenseType;

import java.util.List;

public interface ExpenseTypeContract {

    interface View extends BaseView {
        void createAdapter(List<ExpenseType> expenseTypes);
        void removeItemFromList();
    }
    interface Presenter {
        void setAdapter();
        void deleteExpenseType(ExpenseType expenseType);
     }
}
