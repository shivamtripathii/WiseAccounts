package com.wiselap.accounts.FundTransfer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wiselap.accounts.R;
import com.wiselap.accounts.databinding.ActivityFundTransferBinding;

public class FundTransferActivity extends AppCompatActivity {
    ActivityFundTransferBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fund_transfer);

    }
}
