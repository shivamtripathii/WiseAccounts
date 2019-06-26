package com.wiselap.accounts.home_screen;

import com.wiselap.accounts.interfaces.BaseView;
import com.wiselap.accounts.model.AccountModel;

public interface HomeContract {

    interface View extends BaseView {
        void intentToPersonal(AccountModel accountModel);
        void intentToOffice(AccountModel accountModel);
    }
    interface Presenter{
        void getAccountDetails();
    }
}
