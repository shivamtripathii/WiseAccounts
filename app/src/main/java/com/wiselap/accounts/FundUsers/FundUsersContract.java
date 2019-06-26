package com.wiselap.accounts.FundUsers;

import com.wiselap.accounts.interfaces.BaseView;
import com.wiselap.accounts.model.ShopAgentId;

import java.util.List;

public interface FundUsersContract {

    interface View extends BaseView {
        void setFundUsers(List<FundUsersReturnModel> list);
    }
    interface Presenter {
        void getFundUser(ShopAgentId shopAgentId);
    }
}
