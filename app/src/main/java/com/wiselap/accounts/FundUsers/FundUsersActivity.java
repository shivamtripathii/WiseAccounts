package com.wiselap.accounts.FundUsers;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.wiselap.accounts.FundTransferMethod.FundTransferMethodActivity;
import com.wiselap.accounts.R;
import com.wiselap.accounts.adapters.FundUserAdapter;
import com.wiselap.accounts.base_class.BaseActivity;
import com.wiselap.accounts.databinding.ActivityFundUsersBinding;
import com.wiselap.accounts.model.ShopAgentId;
import com.wiselap.accounts.utils.PreferenceUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class FundUsersActivity extends BaseActivity implements FundUsersContract.View, FundUserAdapter.OnItemViewCLickListener {

    ActivityFundUsersBinding binding;
    ArrayList<FundUsersReturnModel> arrayList=new ArrayList<>();
    FundUserAdapter adapter;
    @Inject
    PreferenceUtils preferenceUtils;
    @Inject
    FundUsersPresenter<FundUsersContract.View> presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_fund_users);
        setToolbar();
        presenter.onAttach(this);
        presenter.getFundUser(new ShopAgentId(preferenceUtils.getShopAgentId()));
    }

    private void setToolbar() {
        binding.toolbar.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.toolbar.delBtn.setVisibility(View.GONE);
        binding.toolbar.addBtn.setVisibility(View.GONE);
        binding.toolbar.editBtn.setVisibility(View.GONE);
        binding.toolbar.title.setText("Users");
    }

    @Override
    public void setFundUsers(List<FundUsersReturnModel> list) {
        arrayList=(ArrayList<FundUsersReturnModel>) list;
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FundUserAdapter(list, this,this);
        binding.recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(int postion) {
        Intent intent=new Intent(this, FundTransferMethodActivity.class);
        intent.putExtra("Fund",arrayList.get(postion));
        startActivity(intent);
    }
}
