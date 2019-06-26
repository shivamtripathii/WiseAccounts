package com.wiselap.accounts.ApplicationSetting;

import com.wiselap.accounts.interfaces.BaseView;

public interface ApplicationSettingContract {
    interface View extends BaseView {
        void intentToConfiguration();
    }
    interface Presenter{
        void saveApproval(int i);
    }

}
