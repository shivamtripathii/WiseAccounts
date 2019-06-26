package com.wiselap.accounts.FundTransferMethod;

import com.wiselap.accounts.base_class.BasePresenterImpl;
import com.wiselap.accounts.interfaces.SchedulerProvider;
import com.wiselap.accounts.utils.PreferenceUtils;

import io.reactivex.disposables.CompositeDisposable;

public class FundMethodPresenter<V extends FundMethodContract.View> extends BasePresenterImpl<V> implements FundMethodContract.Presenter {

    public FundMethodPresenter(SchedulerProvider scheduler, CompositeDisposable disposable, PreferenceUtils preferenceUtils) {
        super(scheduler, disposable, preferenceUtils);
    }
}
