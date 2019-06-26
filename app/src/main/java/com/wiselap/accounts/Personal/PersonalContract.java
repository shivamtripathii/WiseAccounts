package com.wiselap.accounts.Personal;

import com.wiselap.accounts.interfaces.BaseView;
import com.wiselap.accounts.model.AccountModel;

public interface PersonalContract {
    interface View extends BaseView {
        void intentToHome();
    }
    interface Presenter{
        void sendData(String name, String contact, String address);
        String getEmailId();

        void updateData(AccountModel accountModel, String name, String contact, String address);
    }

}
