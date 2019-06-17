package com.wiselap.accounts.expense.AddExpensePackage;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;

import com.google.gson.Gson;
import com.shashank.sony.fancytoastlib.FancyToast;
import com.wiselap.accounts.DatePickerFragment;
import com.wiselap.accounts.R;
import com.wiselap.accounts.base_class.BaseActivity;
import com.wiselap.accounts.constants.AppConstants;
import com.wiselap.accounts.databinding.ActivityAddExpenseBinding;
import com.wiselap.accounts.expense.ExpensePackage.ExpenseReturnModel;
import com.wiselap.accounts.utils.PreferenceUtils;
import com.wiselap.accounts.utils.RealPathUtil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

public class AddExpenseActivity extends BaseActivity implements DatePickerDialog.OnDateSetListener, AddExpenseContract.view, AdapterView.OnItemSelectedListener {

    private static final int PICK = 100;
    ActivityAddExpenseBinding activityAddExpenseBinding;
    Uri imageUri;

    @Inject
    AddExpensePresenter<AddExpenseContract.view> presenter;
    private ArrayAdapter<String> adapter;
    @Inject
    PreferenceUtils preferenceUtils;
    ExpenseReturnModel expenseReturnModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        activityAddExpenseBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_expense);
        initToolBar();
        presenter.onAttach(this);
        presenter.setAdapter();
        if (getIntent().getStringExtra(AppConstants.Operation).equals(AppConstants.EDIT)) {
            expenseReturnModel = (ExpenseReturnModel) getIntent().getSerializableExtra("edit");
            Log.d("Delete", "onCreate: " + expenseReturnModel.getExpense_name());
            activityAddExpenseBinding.dateID.setText(dateConvertMM(expenseReturnModel.getDate()));

            activityAddExpenseBinding.remarkID.setText(expenseReturnModel.getRemarks());
            activityAddExpenseBinding.amountID.setText("" + expenseReturnModel.getExpense_amount());
        } else if (getIntent().getStringExtra(AppConstants.Operation).equals(AppConstants.ADD)) {
            String todaysDate = getIntent().getStringExtra("Date1");
            activityAddExpenseBinding.dateID.setText(todaysDate);
        }
    }

    private void initToolBar() {
        activityAddExpenseBinding.expenseEditToolbar.title.setText("Define Expense");
        activityAddExpenseBinding.expenseEditToolbar.addBtn.setVisibility(View.GONE);
        activityAddExpenseBinding.expenseEditToolbar.editBtn.setVisibility(View.GONE);
        activityAddExpenseBinding.expenseEditToolbar.delBtn.setVisibility(View.GONE);
    }

    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.backBtn:
                finish();
                break;

            case R.id.add_expense_ID:
                Intent intent = new Intent();
                String date = activityAddExpenseBinding.dateID.getText().toString().trim();
                String expense = activityAddExpenseBinding.expenseID.getSelectedItem().toString();
                if (expense.equals(""))
                    showText(this, "No Expense Type Found");
                else {
                    String remarks = activityAddExpenseBinding.remarkID.getText().toString().trim();
                    long amount;
                    if (activityAddExpenseBinding.amountID.getText().toString().trim().equals(""))
                        amount = 0;
                    else
                        amount = Long.parseLong(activityAddExpenseBinding.amountID.getText().toString().trim());
                    if (getIntent().getStringExtra(AppConstants.Operation).equals(AppConstants.EDIT)) {
                        ExpenseReturnModel expenseReturnModel = (ExpenseReturnModel) getIntent().getSerializableExtra("edit");
                        presenter.updateExpense(new UpdateExpenseMethodModel(dateConvertMMM(date), expense, amount, remarks, preferenceUtils.getLoginId(), presenter.checkExpenseTypeID(expense), preferenceUtils.getAccountingProfile(), expenseReturnModel.getExpenseId()));
                    } else {
                        presenter.addExpense(new AddExpenseMethodModel(dateConvertMMM(date), expense, amount, remarks, preferenceUtils.getLoginId(), presenter.checkExpenseTypeID(expense), preferenceUtils.getAccountingProfile()));
                    }
                    setResult(RESULT_OK, intent);
                    finish();
                }
                break;

            case R.id.date_ID:
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "Date Picker");
                break;


            case R.id.upload_btn_ID:
                openGallery();
                break;

        }
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            String path = RealPathUtil.getRealPathFromURI_API19(this, imageUri);
            FancyToast.makeText(this, "Path = " + path, 10, FancyToast.SUCCESS, false).show();
            activityAddExpenseBinding.uploadImageID.setImageURI(imageUri);
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
        String date = simpleDateFormat.format(calendar.getTime());
        activityAddExpenseBinding.dateID.setText(date);
    }


    @Override
    public void sendExpenseType(ArrayList<String> expenseType) {
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, expenseType);
        Log.d("spinner", "sendExpenseType: " + new Gson().toJson(expenseType));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        activityAddExpenseBinding.expenseID.setAdapter(adapter);
        if (getIntent().getStringExtra(AppConstants.Operation).equals(AppConstants.EDIT))
            activityAddExpenseBinding.expenseID.setSelection(adapter.getPosition(expenseReturnModel.getExpense_name()));
        activityAddExpenseBinding.expenseID.setOnItemSelectedListener(this);
    }

    @Override
    public void updateExpense() {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        showText(this, "Selected");
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    // MM to MMM Date
    public String dateConvertMM(String date)  {
        Date date1 = null;
        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String finaldate=new SimpleDateFormat("dd MMM yyyy",Locale.getDefault()).format(date1);
        return finaldate;
    }
    //MMM to MM Date
    public String dateConvertMMM(String date)
    {
        Date date1= null;
        try {
            date1 = new SimpleDateFormat("dd MMM yyyy",Locale.getDefault()).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String finaldate=new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault()).format(date1);
        return finaldate;
    }
}
