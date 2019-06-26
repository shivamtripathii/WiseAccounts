package com.wiselap.accounts.FundTransfer;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wiselap.accounts.FundUsers.FundUsersActivity;
import com.wiselap.accounts.R;
import com.wiselap.accounts.adapters.FundTransferAdapter;
import com.wiselap.accounts.base_class.BaseActivity;
import com.wiselap.accounts.databinding.ActivityFundTransferBinding;

public class FundTransferActivity extends BaseActivity implements FundTransferAdapter.OnFundItemViewCLickListener {
    ActivityFundTransferBinding binding;
    String[] item = {"Users", "Vendor Payment", "Salary Payment"};
    int[] images =
            {R.drawable.ic_money,
                    R.drawable.ic_money,
                    R.drawable.ic_money
            };
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_fund_transfer);
        setToolBar();
        recyclerView = binding.fundrecyclerView;
        FundTransferAdapter fundTransferAdapter=new FundTransferAdapter(this,item,images,this);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        recyclerView.setAdapter(fundTransferAdapter);
    }

    private void setToolBar() {
        binding.toolbar.title.setText("Fund Transfer");
        binding.toolbar.editBtn.setVisibility(View.GONE);
        binding.toolbar.addBtn.setVisibility(View.GONE);
        binding.toolbar.delBtn.setVisibility(View.GONE);
        binding.toolbar.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onItemClick(int postion) {
        if (postion == 0) {
            startActivity(new Intent(this, FundUsersActivity.class));
        }
    }
}
