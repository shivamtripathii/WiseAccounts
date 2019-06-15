package com.wiselap.accounts.expense.ExpensePackage;

import com.wiselap.accounts.interfaces.BaseView;

import java.util.List;

public interface ExpensesContract {

    interface View extends BaseView {
        void expenseEdit(android.view.View view, int i);
        void delete(int i);
        void setExpense(List<ExpenseReturnModel> list);
        void refresh();
    }
    interface Presenter{
        void getExpense(ExpenseMethodModel expenseMethodModel);
        void deleteExpense(DeleteExpenseMethodModel model);
    }
}
