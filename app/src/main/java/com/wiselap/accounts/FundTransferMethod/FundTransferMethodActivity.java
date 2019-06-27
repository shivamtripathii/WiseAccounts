package com.wiselap.accounts.FundTransferMethod;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.wiselap.accounts.FundTransfer.FundTransferActivity;
import com.wiselap.accounts.FundUsers.FundUsersReturnModel;
import com.wiselap.accounts.R;
import com.wiselap.accounts.base_class.BaseActivity;
import com.wiselap.accounts.databinding.ActivityFundTransferMethodBinding;
import com.wiselap.accounts.utils.PreferenceUtils;

import javax.inject.Inject;

public class FundTransferMethodActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {
    ActivityFundTransferMethodBinding binding;
    FundUsersReturnModel model;
    ArrayAdapter<CharSequence> adapter;
    Long amount;
    @Inject
    PreferenceUtils preferenceUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_fund_transfer_method);
        setToolbar();

        model=(FundUsersReturnModel)getIntent().getSerializableExtra("Fund");
        binding.name.setText(model.getShopAgentName());
        binding.amount.setText(String.valueOf(model.getAdvance()));
        adapter();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.next:
                if(binding.payMode.getSelectedItem().equals("Bank Account"))
                {
                    String bankname=binding.bankname.getText().toString();
                    Long accNo=Long.parseLong(binding.accountnumber.getText().toString());
                    String ifscNo=binding.ifscnumber.getText().toString();

                    new BankModel(model.getShopAgentId(),model.getAdvance(),preferenceUtils.getShopAgentId(),bankname,accNo,ifscNo,"Some Remark");
                    startActivity(new Intent(this, FundTransferActivity.class));
                }
                else if(binding.payMode.getSelectedItem().equals("UPI"))
                {
                    String name=binding.nameUpi.getText().toString();
                    String upiId=binding.upiId.getText().toString();

                    new UPIModel(model.getShopAgentId(),model.getAdvance(),"Upi",preferenceUtils.getShopAgentId(),upiId,name,"34590xcv","paytm","some remark");
                    startActivity(new Intent(this, FundTransferActivity.class));
                }
                else if(binding.payMode.getSelectedItem().equals("Cash"))
                {
                    startActivity(new Intent(this, FundTransferActivity.class));
                }
        }
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
        if(text.equals("UPI"))
        {
            binding.ll3.setVisibility(View.GONE);
            binding.ll2.setVisibility(View.VISIBLE);
        }
        else if(text.equals("Bank Account"))
        {
            binding.ll2.setVisibility(View.GONE);
            binding.ll3.setVisibility(View.VISIBLE);
        }
        else if(text.equals("Cash"))
        {
            binding.ll2.setVisibility(View.GONE);
            binding.ll3.setVisibility(View.GONE);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
