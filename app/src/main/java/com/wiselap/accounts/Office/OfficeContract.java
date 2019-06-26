package com.wiselap.accounts.Office;

import com.wiselap.accounts.interfaces.BaseView;
import com.wiselap.accounts.model.AccountModel;

public interface OfficeContract {
    interface View extends BaseView {
        void intentToHome();
    }
    interface Presenter{
        void sendData(String office, String contact, String owner, String address);
        void updateData(AccountModel accountModel, String office, String contact,
                        String owner, String address);
        String getEmailId();
    }
}
