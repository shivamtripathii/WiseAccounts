package com.wiselap.accounts.SignIn;

import com.wiselap.accounts.interfaces.BaseView;
import com.wiselap.accounts.model.Accounts;
import com.wiselap.accounts.model.LoginId;
import com.wiselap.accounts.model.AuthenticationUsingEmail;

import java.util.ArrayList;

public interface LoginContract {
    interface view extends BaseView{
        void intentToAccounts(ArrayList<Accounts>accounts);
        void intentToSelectEntitiy();
    }
    interface  presenter{
        void sendEmailId(AuthenticationUsingEmail authenticationUsingEmail);

        void getAccounts(LoginId loginId);

    }
}
