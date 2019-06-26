package com.wiselap.accounts.FundTransfer;

import com.wiselap.accounts.base_class.BasePresenterImpl;
import com.wiselap.accounts.interfaces.SchedulerProvider;
import com.wiselap.accounts.utils.PreferenceUtils;

import io.reactivex.disposables.CompositeDisposable;

public class FundPresenter<V extends FundContract.View> extends BasePresenterImpl<V> implements FundContract.Presenter {

    public FundPresenter(SchedulerProvider scheduler, CompositeDisposable disposable, PreferenceUtils preferenceUtils) {
        super(scheduler, disposable, preferenceUtils);
    }
}
