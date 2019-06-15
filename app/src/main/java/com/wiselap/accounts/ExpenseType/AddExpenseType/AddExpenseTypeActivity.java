package com.wiselap.accounts.ExpenseType.AddExpenseType;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;

import com.wiselap.accounts.R;
import com.wiselap.accounts.base_class.BaseActivity;
import com.wiselap.accounts.constants.AppConstants;
import com.wiselap.accounts.databinding.ActivityAddExpenseTypeBinding;
import com.wiselap.accounts.model.ExpenseType;

import java.util.List;

import javax.inject.Inject;

/**
 * @author Aprataksh Anand
 *
 * Activity to handle both add and edit Expense Type
 *
 * if Operation == ADD
 *      manages AddExpenseType
 *
 * if Operation == EDIT
 *      manages EditExpenseType
 * */

public class AddExpenseTypeActivity extends BaseActivity implements AddExpenseTypeContract.View {

    ActivityAddExpenseTypeBinding binding;
    @Inject
    AddExpenseTypePresenter<AddExpenseTypeContract.View> mPresenter;

    static String Operation;

    ArrayAdapter<String> adapter;
    Integer expenseTypeId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        receiveData();

    }

    /**
     * Receiving the type of operation*/

    private void receiveData() {
        Operation = getIntent().getStringExtra(AppConstants.Operation);

        if(Operation.equals(AppConstants.ADD))
            binding.toolbar.title.setText(getString(R.string.Add_Expense_Type));
        else{
            binding.toolbar.title.setText(getString(R.string.Edit_Expense_Type));
            binding.save.setText(getString(R.string.Update));
            ExpenseType expenseType = (ExpenseType) getIntent().getSerializableExtra(AppConstants.Expense_Type_Name);
            setFields(expenseType);
        }
    }

    /**
     * Setting up the presenter and layout*/
    private void init() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_expense_type);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        setSpinner();
        binding.amountEdit.setText("0");

        mPresenter.onAttach(this);
    }

    /**
     * Setting spinner for frequency type
     * */
    private void setSpinner() {
        adapter = new ArrayAdapter<String>(AddExpenseTypeActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.frequency));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.frequencyEdit.setAdapter(adapter);
    }

    /**
     * Manages the onClick for buttons
     * */
    @Override
    public void onClick(View v) {
        super.onClick(v);

        switch (v.getId()){
            case R.id.backBtn:
                finish();
                break;
            case R.id.save:
                if (validExpenseType()) {

                    String expenseName = binding.expenseNameEdit.getText().toString().trim();
                    String frequencyName = binding.frequencyEdit.getSelectedItem().toString();
                    String defaultAmount = binding.amountEdit.getText().toString();
                    double defAmount;

                    if (defaultAmount.equals(""))
                        defAmount = 0;
                    else
                        defAmount = Double.parseDouble(defaultAmount);

                    ExpenseType expenseType = new ExpenseType(null, expenseName, frequencyName, defAmount);

                    if(Operation.equals(AppConstants.ADD)) {
                        mPresenter.addExpenseType(expenseType);
                    }
                    else {
                        expenseType.setId(expenseTypeId);
                        mPresenter.updateExpenseType(expenseType);
                    }


                }
                break;
        }
    }

    @Override
    public void goToList() {
        Intent intent = new Intent();
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    /**
     * Sanity checks on input of fields
     * */

    boolean validExpenseType(){
        boolean isValid = true;
        String expense_name = binding.expenseNameEdit.getText().toString().trim();

        List<ExpenseType> expenseTypeList = mPresenter.fetchExpenseTypeList();

        if(expense_name.equals("")) {
            binding.expenseNameEdit.setError(getString(R.string.Expense_Not_Blank));
            isValid = false;
        }
        if(expenseTypeList != null){
            for(ExpenseType row : expenseTypeList){
                if(row.getExpense_name().equalsIgnoreCase(expense_name)){
                    binding.expenseNameEdit.setError(expense_name + getString(R.string.Already_Exists));
                    isValid = false;
                }
            }
        }
        return isValid;
    }

    /**
     * Setting up of the fields
     * */

    @Override
    public void setFields(ExpenseType expenseItem) {
        expenseTypeId = expenseItem.getId();
        binding.expenseNameEdit.setText(expenseItem.getExpense_name());
        binding.expenseNameEdit.append("");
        binding.frequencyEdit.setSelection(adapter.getPosition(expenseItem.getFrequency()));
        binding.amountEdit.setText(String.valueOf(expenseItem.getAmount()));
    }
}
