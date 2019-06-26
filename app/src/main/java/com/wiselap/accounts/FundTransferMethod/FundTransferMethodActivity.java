package com.wiselap.accounts.FundTransferMethod;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.wiselap.accounts.FundUsers.FundUsersReturnModel;
import com.wiselap.accounts.R;
import com.wiselap.accounts.base_class.BaseActivity;
import com.wiselap.accounts.databinding.ActivityFundTransferMethodBinding;

public class FundTransferMethodActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {
    ActivityFundTransferMethodBinding binding;
    FundUsersReturnModel model;
    ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_fund_transfer_method);
        setToolbar();
        model=(FundUsersReturnModel)getIntent().getSerializableExtra("Fund");
        binding.name.setText(model.getShopAgentName());
        binding.amount.setText(String.valueOf(model.getAdvance()));
        adapter();
    }

    private void setToolbar() {
        binding.toolbar.title.setText("Fund Transfer");
        binding.toolbar.editBtn.setVisibility(View.GONE);
        binding.toolbar.delBtn.setVisibility(View.GONE);
        binding.toolbar.addBtn.setVisibility(View.GONE);
        binding.toolbar.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void adapter() {
        adapter = ArrayAdapter.createFromResource(this, R.array.paymode, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.payMode.setAdapter(adapter);
        binding.payMode.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text=parent.getItemAtPosition(position).toString();
        showMessage(text);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
