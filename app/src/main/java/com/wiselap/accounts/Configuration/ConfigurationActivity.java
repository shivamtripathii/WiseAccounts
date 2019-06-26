package com.wiselap.accounts.Configuration;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.wiselap.accounts.ApplicationSetting.ApplicationSettingActivity;
import com.wiselap.accounts.ExpenseType.ExpenseTypeActivity;
import com.wiselap.accounts.R;
import com.wiselap.accounts.adapters.ConfigurationAdapter;
import com.wiselap.accounts.base_class.BaseActivity;
import com.wiselap.accounts.databinding.ActivityConfigurationBinding;
import com.wiselap.accounts.model.Configuration;
import com.wiselap.accounts.utils.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * @author Aprataksh Anand
 * Activity for Configuration Module
 *
 * EXPENSE_TYPE : onClick for Expense Type
 * */

public class ConfigurationActivity extends BaseActivity implements ConfigurationContract.View, ConfigurationAdapter.OnConfigurationListener {


    final int EXPENSE_TYPE = 0;
    final int APPLICATION_SETTING = 1;


    private List<Configuration> confList = new ArrayList<>();
    private ConfigurationAdapter adapter;
    @Inject
    ConfigurationPresenter<ConfigurationContract.View> mPresenter;
    ActivityConfigurationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_configuration);

        binding.toolbar.title.setText(getString(R.string.Configuration));

        initList();

        mPresenter.onAttach(this);

        mPresenter.setAdapter();
    }

    /**
     * Setting the adapter
     * */
    @Override
    public void createAdapter() {
        binding.recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        binding.recyclerView.addItemDecoration(new SpacesItemDecoration(20));
        adapter = new ConfigurationAdapter(confList, this, this);
        binding.recyclerView.setAdapter(adapter);
    }

    private void initList(){
        confList.add(new Configuration(R.drawable.ic_attach_money_black_24dp, getString(R.string.Expense_Type)));
        confList.add(new Configuration(R.drawable.ic_settings_gears, "Application Settings"));
    }

    @Override
    public void onConfigurationClick(int position) {
        switch (position){
            case EXPENSE_TYPE:
                startActivity(new Intent(this, ExpenseTypeActivity.class));
                finish();
                break;
            case APPLICATION_SETTING:
                startActivity(new Intent(this, ApplicationSettingActivity.class));
                finish();
                break;
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.backBtn:
                finish();
                break;
        }
    }
}