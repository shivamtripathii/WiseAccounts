package com.wiselap.accounts.users.AddUsersPackage;

import com.wiselap.accounts.interfaces.BaseView;

public interface AddUserContract {
    interface view extends BaseView {
        void sendMeta(int meta);
    }
    interface  presenter{
        void getMeta(AddUserMethodModel addUserMethodModel);
        void updateUser(UpdateUserMethodModel model);

    }
}
