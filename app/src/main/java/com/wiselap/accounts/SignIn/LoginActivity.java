package com.wiselap.accounts.SignIn;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.shashank.sony.fancytoastlib.FancyToast;
import com.wiselap.accounts.Google_signIn_Initializations;
import com.wiselap.accounts.R;
import com.wiselap.accounts.Select_Account.SelectAccountActivity;
import com.wiselap.accounts.Select_Entity.SelectEntityActivity;
import com.wiselap.accounts.base_class.BaseActivity;
import com.wiselap.accounts.constants.AppConstants;
import com.wiselap.accounts.databinding.ActivityGoogleSignInBinding;
import com.wiselap.accounts.home_screen.Homepage;
import com.wiselap.accounts.model.Accounts;
import com.wiselap.accounts.model.ApplicationUserID;
import com.wiselap.accounts.model.AuthenticationUsingEmail;
import com.wiselap.accounts.utils.PreferenceUtils;

import java.util.ArrayList;

import javax.inject.Inject;

public class LoginActivity extends BaseActivity implements LoginContract.view {

    private static final String TAG = "GoogleSignIN";
    private static final int  RC_SIGN_IN = 1;
    private ActivityGoogleSignInBinding binding;
    private GoogleSignInAccount account;
    @Inject
    PreferenceUtils preferenceUtils;

    @Inject
    LoginPresenter<LoginContract.view> presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_google_sign_in);
        init();
        events();
    }

    private void events() {
        binding.signInButton.setOnClickListener((v)->{
            Intent signInIntent = Google_signIn_Initializations.getInstance(LoginActivity.this).getmGoogleSignInClient().getSignInIntent();
            startActivityForResult(signInIntent, RC_SIGN_IN);
        });
    }

    private void init() {
        presenter.onAttach(this);
        account = GoogleSignIn.getLastSignedInAccount(this);
         if (!preferenceUtils.getEmailId().isEmpty() && preferenceUtils.getAccountingProfile()!=0){
            intentToHomeScreen();
        }else if(!preferenceUtils.getEmailId().isEmpty() && preferenceUtils.getAccountingProfile() == 0){
            presenter.getAccounts(new ApplicationUserID(preferenceUtils.getApplicationUSerId()));
        }
    }

    private void intentToHomeScreen() {
        startActivity(new Intent(this, Homepage.class));
        finish();
    }

    private void handleSignInResult(Task<GoogleSignInAccount> task) {
        try {
            account = task.getResult(ApiException.class);
            presenter.sendEmailId(new AuthenticationUsingEmail(account.getEmail()));
        } catch (ApiException e) {
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    @Override
    public void intentToAccounts(ArrayList<Accounts> accounts) {
        Intent intentToAccounts = new Intent(this, SelectAccountActivity.class);
        intentToAccounts.putExtra(AppConstants.ACCOUNTS,accounts);
        startActivity(intentToAccounts);
        finish();
    }

    @Override
    public void intentToSelectEntitiy() {
        startActivity(new Intent(this, SelectEntityActivity.class));
        finish();
    }

    public void onClick(View view){
        FancyToast.makeText(LoginActivity.this,"SignOut.", 20,FancyToast.ERROR,false).show();
    }

}
