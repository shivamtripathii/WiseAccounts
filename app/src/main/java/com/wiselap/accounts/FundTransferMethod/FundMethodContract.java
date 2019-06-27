package com.wiselap.accounts.FundTransferMethod;

import com.wiselap.accounts.interfaces.BaseView;

public interface FundMethodContract {
    interface View extends BaseView {

    }
    interface Presenter {
        void doPaymentBank(BankModel bankModel);
        void doPaymentBank(CashModel cashModel);
        void doPaymentBank(UPIModel upiModel);
    }
}
