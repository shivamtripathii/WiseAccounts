package com.wiselap.accounts.home_screen;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.wiselap.accounts.Approval.ApprovalDefaultActivity;
import com.wiselap.accounts.Configuration.ConfigurationActivity;
import com.wiselap.accounts.FundTransfer.FundTransferActivity;
import com.wiselap.accounts.Office.OfficeActivity;
import com.wiselap.accounts.Personal.PersonalActivity;
import com.wiselap.accounts.Report.ReportActivity;
import com.wiselap.accounts.SignIn.LoginActivity;

import com.wiselap.accounts.R;
import com.wiselap.accounts.adapters.HomepageAdapter;
import com.wiselap.accounts.base_class.BaseActivity;
import com.wiselap.accounts.constants.AppConstants;
import com.wiselap.accounts.databinding.ActivityHomepageBinding;
import com.wiselap.accounts.expense.ExpensePackage.ExpensesActivity;
import com.wiselap.accounts.model.AccountModel;
import com.wiselap.accounts.users.UsersPackage.UsersActivity;
import com.wiselap.accounts.utils.PreferenceUtils;

import javax.inject.Inject;

public class Homepage extends BaseActivity implements HomepageAdapter.OnItemViewCLickListener,HomeContract.View {
    GoogleSignInClient mGoogleSignInClient;
    String[] item = {"Users", "Expenses", "Reports", "Configuration", "Approval","Fund Transfer"};
    int[] images =
            {R.drawable.ic_person_black_24dp,
                    R.drawable.expense,
                    R.drawable.report,
                    R.drawable.configuration,
                    R.drawable.approval,
            R.drawable.ic_monetization_on_black_24dp
           };
    RecyclerView recyclerView;
    Toolbar toolbar;
    ActivityHomepageBinding binding;
    @Inject
    PreferenceUtils preferenceUtils;
    @Inject
    HomePresenter<HomeContract.View> mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_homepage);
        mPresenter.onAttach(this);
        recyclerView = findViewById(R.id.recycler_view);
        toolbar = findViewById(R.id.toolbar_home);
        toolbar.setTitle("Homepage");
        HomepageAdapter recyclerViewAdapter = new HomepageAdapter(this, item, images, this);
        //recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerViewAdapter);
        binding.toolbarHome.backBtn.setImageResource(R.drawable.home);
        binding.toolbarHome.addBtn.setVisibility(View.GONE);

        binding.toolbarHome.editBtn.setImageResource(R.drawable.ic_account_circle_black_24dp);
        binding.toolbarHome.delBtn.setImageResource(R.drawable.exit);
        binding.toolbarHome.title.setText("Home");
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.del_btn:
                AlertBox();
                break;
            case R.id.edit_btn:
                mPresenter.getAccountDetails();

        }
    }



    @Override
    public void onItemClick(int postion) {
        if (postion == 0) {
            startActivity(new Intent(this, UsersActivity.class));
            //finish();
        } else if (postion == 1) {
            startActivity(new Intent(this, ExpensesActivity.class));
            //finish();
        } else if (postion == 2) {
            startActivity(new Intent(this, ReportActivity.class));
            //finish();
        } else if (postion == 3) {
            startActivity(new Intent(this, ConfigurationActivity.class));
            //finish();
        }
        else if (postion == 4) {
            startActivity(new Intent(this, ApprovalDefaultActivity.class));
            //finish();
        }
        else if (postion == 5) {
            startActivity(new Intent(this, FundTransferActivity.class));
            //finish();
        }

    }

    private void AlertBox() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Google Logout").setMessage("Do you want to Logout?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(getString(R.string.default_web_client_id))
                        .requestEmail()
                        .build();

                mGoogleSignInClient = GoogleSignIn.getClient(Homepage.this, gso);
                mGoogleSignInClient.signOut();
                preferenceUtils.clearAll();
                startActivity(new Intent(Homepage.this, LoginActivity.class));
                finish();
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        }).show();
    }

    @Override
    public void intentToPersonal(AccountModel accountModel) {
        Intent intent = new Intent(this, PersonalActivity.class);
        intent.putExtra(AppConstants.Operation, AppConstants.EDIT);
        intent.putExtra(AppConstants.profile, accountModel);
        startActivity(intent);
    }

    @Override
    public void intentToOffice(AccountModel accountModel) {
        Intent intent = new Intent(this, OfficeActivity.class);
        intent.putExtra(AppConstants.Operation, AppConstants.EDIT);
        intent.putExtra(AppConstants.profile, accountModel);
        startActivity(intent);
    }
}
