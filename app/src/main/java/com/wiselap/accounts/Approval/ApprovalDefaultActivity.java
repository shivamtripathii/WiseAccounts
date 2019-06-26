package com.wiselap.accounts.Approval;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.wiselap.accounts.Approval.ApprovalDetails.ApprovalDetailsActivity;
import com.wiselap.accounts.R;
import com.wiselap.accounts.adapters.ApprovalDefaultAdapter;
import com.wiselap.accounts.base_class.BaseActivity;
import com.wiselap.accounts.constants.AppConstants;
import com.wiselap.accounts.databinding.ActivityApprovalDefaultBinding;
import com.wiselap.accounts.model.ApprovalUserModel;

import java.util.ArrayList;

import javax.inject.Inject;

public class ApprovalDefaultActivity extends BaseActivity implements ApprovalDefaultContract.View, ApprovalDefaultAdapter.OnApprovalListener {


    @Inject
    ApprovalDefaultPresenter<ApprovalDefaultContract.View> mPresenter;


    private ArrayList<ApprovalUserModel> arrayList = new ArrayList<>();
    ApprovalDefaultAdapter adapter;

    ActivityApprovalDefaultBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_approval_default);

        mPresenter.onAttach(this);
        binding.toolbar.title.setText(getString(R.string.Approval1));

        initList();

        initRecyclerView();

    }

    private void initRecyclerView() {
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ApprovalDefaultAdapter(arrayList, this, this);
        binding.recyclerView.setAdapter(adapter);
    }

    private void initList() {
        arrayList.add(new ApprovalUserModel("Mahesh Singhania", 5000.0));
        arrayList.add(new ApprovalUserModel("Ramesh Patil", 800.0));
        arrayList.add(new ApprovalUserModel("Shree Kale", 3200.0));
    }

    @Override
    public void onApprovalClick(int position) {

        Intent intent = new Intent(this, ApprovalDetailsActivity.class);
        intent.putExtra(AppConstants.approvalItem, arrayList.get(position));
        startActivity(intent);
        finish();
    }
}
