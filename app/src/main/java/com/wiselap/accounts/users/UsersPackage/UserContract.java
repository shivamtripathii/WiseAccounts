package com.wiselap.accounts.users.UsersPackage;

import android.view.View;

import com.wiselap.accounts.interfaces.BaseView;

import java.util.List;

public interface UserContract {
    interface view extends BaseView {
        void setUsers(List<UserReturnModel> list);
        void userEdit(View view, int i);
        void refresh();
    }
    interface  presenter{
        void getUsers(UserMethodModel userMethodModel);
        long getApplicationUserId();
        void deleteUser(DeleteUserMethodModel model);

    }
}
