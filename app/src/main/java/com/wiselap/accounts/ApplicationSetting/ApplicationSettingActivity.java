package com.wiselap.accounts.ApplicationSetting;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.wiselap.accounts.Configuration.ConfigurationActivity;
import com.wiselap.accounts.ExpenseType.AddExpenseType.AddExpenseTypeActivity;
import com.wiselap.accounts.R;
import com.wiselap.accounts.base_class.BaseActivity;
import com.wiselap.accounts.databinding.ActivityApplicationSettingBinding;
import com.wiselap.accounts.model.Configuration;

import javax.inject.Inject;

public class ApplicationSettingActivity extends BaseActivity implements ApplicationSettingContract.View {


    @Inject
    ApplicationSettingPresenter<ApplicationSettingContract.View> mPresenter;

    ArrayAdapter<String> adapter;
    ActivityApplicationSettingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_application_setting);
        binding.toolbar.title.setText(getString(R.string.ApplicationSetting));
        mPresenter.onAttach(this);

        setSpinner();
    }

    private void setSpinner() {
        adapter = new ArrayAdapter<String>(ApplicationSettingActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.YesNo));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.YesNo.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

        switch (v.getId()){
            case R.id.backBtn:
                startActivity(new Intent(this, ConfigurationActivity.class));
                finish();
                break;
            case R.id.save:
                int i = binding.YesNo.getSelectedItemPosition();
                mPresenter.saveApproval(i);
        }
    }

    @Override
    public void intentToConfiguration() {
        startActivity(new Intent(this, ConfigurationActivity.class));
        finish();
    }
}
