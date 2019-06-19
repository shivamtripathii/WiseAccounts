package com.wiselap.accounts.users.UsersPackage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.shashank.sony.fancytoastlib.FancyToast;
import com.wiselap.accounts.R;
import com.wiselap.accounts.adapters.UsersAdapter;
import com.wiselap.accounts.base_class.BaseActivity;
import com.wiselap.accounts.constants.AppConstants;
import com.wiselap.accounts.databinding.ActivityUsersBinding;
import com.wiselap.accounts.users.AddUsersPackage.AddUsersActivity;
import com.wiselap.accounts.utils.PreferenceUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class UsersActivity extends BaseActivity implements UserContract.view {

    ActivityUsersBinding activityUsersBinding;
    ArrayList<UserReturnModel> arrayList = new ArrayList<>();
    UsersAdapter usersAdapter;
    @Inject
    PreferenceUtils preferenceUtils;
    @Inject
    UserPresenter<UserContract.view> presenter;

    private int k = -1;
    ArrayList<UserReturnModel> expenseList = new ArrayList<>();
    ArrayList<View> viewList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityUsersBinding = DataBindingUtil.setContentView(this, R.layout.activity_users);
        initViews();
        presenter.onAttach(this);
        presenter.getUsers(new UserMethodModel(preferenceUtils.getAccountingProfile(),preferenceUtils.getShopAgentId()));
    }

    private void initViews() {
        //addUsers();
        //usersAdapter = new UsersAdapter(arrayList, this);

        activityUsersBinding.user1Toolbar.title.setText("Users");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String name, userid;
        String profile;
        if (requestCode == 1) {
            if (resultCode != RESULT_OK)
                presenter.getUsers(new UserMethodModel(preferenceUtils.getAccountingProfile(),preferenceUtils.getShopAgentId()));
        } else {
            if (resultCode != RESULT_OK)
                presenter.getUsers(new UserMethodModel(preferenceUtils.getAccountingProfile(),preferenceUtils.getShopAgentId()));
            viewList.get(0).setBackgroundResource(R.color.colorwhite);
            viewList.clear();
            expenseList.clear();
            activityUsersBinding.user1Toolbar.addBtn.setVisibility(View.VISIBLE);
            k = -1;
            FancyToast.makeText(this, "Error Found", 10, FancyToast.CONFUSING, R.drawable.ic_check_black_24dp, false);
        }
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_btn:
                Intent intent = new Intent(UsersActivity.this, AddUsersActivity.class);
                intent.putExtra(AppConstants.Operation, AppConstants.ADD);
                startActivityForResult(intent, 1);
                //finish();
                break;
            case R.id.edit_btn:
                if (k != -1) {
                    Intent intentAdd = new Intent(UsersActivity.this, AddUsersActivity.class);
                    intentAdd.putExtra("users", arrayList.get(k));
                    intentAdd.putExtra(AppConstants.Operation, AppConstants.EDIT);
                    startActivityForResult(intentAdd, 2);
                }
                else
                {
                    showText(this,"Select user to edit");
                }
                break;
            case R.id.backBtn:
                //startActivity(new Intent(UsersActivity.this, Homepage.class));
                finish();
                break;
            case R.id.del_btn:
                if(k!=-1)
                {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(UsersActivity.this);
                    alertDialogBuilder.setTitle("").setMessage("Do you want to Delete ?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            presenter.deleteUser(new DeleteUserMethodModel(preferenceUtils.getAccountingProfile(),arrayList.get(k).getShopAgentId(),arrayList.get(k).getUserId()));
                        }
                    }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    }).show();
                }
                break;
            default:
                FancyToast.makeText(this, "Error on Click", 10, FancyToast.CONFUSING, R.drawable.ic_check_black_24dp, false);
        }

    }

    @Override
    public void setUsers(List<UserReturnModel> list) {
        arrayList = (ArrayList<UserReturnModel>) list;
        activityUsersBinding.userRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        usersAdapter = new UsersAdapter((ArrayList<UserReturnModel>) list, this);
        activityUsersBinding.userRecyclerView.setAdapter(usersAdapter);
        usersAdapter.notifyDataSetChanged();
    }

    @Override
    public void userEdit(View v, int i) {
        if (k == -1) {
            v.setBackgroundResource(R.color.OnSelected);
            viewList.add(v);
            expenseList.add(arrayList.get(i));
            activityUsersBinding.user1Toolbar.addBtn.setVisibility(View.GONE);
            k = i;
        } else if (k == i) {
            v.setBackgroundResource(R.color.colorwhite);
            viewList.clear();
            expenseList.clear();
            activityUsersBinding.user1Toolbar.addBtn.setVisibility(View.VISIBLE);
            k = -1;
        } else {
            if (!viewList.isEmpty()) {
                viewList.get(0).setBackgroundResource(R.color.colorwhite);
                viewList.clear();
            }
            v.setBackgroundResource(R.color.OnSelected);
            if (!expenseList.isEmpty())
                expenseList.clear();
            expenseList.add(arrayList.get(i));
            viewList.add(v);
            activityUsersBinding.user1Toolbar.addBtn.setVisibility(View.GONE);
            k = i;
        }
    }

    @Override
    public void refresh() {
        viewList.get(0).setBackgroundResource(R.color.colorwhite);
        viewList.clear();
        expenseList.clear();
        activityUsersBinding.user1Toolbar.addBtn.setVisibility(View.VISIBLE);
        k = -1;
        presenter.getUsers(new UserMethodModel(preferenceUtils.getAccountingProfile(),preferenceUtils.getShopAgentId()));
    }
}
