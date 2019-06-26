package com.wiselap.accounts.Approval;

import com.wiselap.accounts.base_class.BasePresenterImpl;
import com.wiselap.accounts.interfaces.SchedulerProvider;
import com.wiselap.accounts.utils.PreferenceUtils;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class ApprovalDefaultPresenter<V extends ApprovalDefaultContract.View> extends BasePresenterImpl<V> implements ApprovalDefaultContract.Presenter {

    @Inject
    public ApprovalDefaultPresenter(SchedulerProvider scheduler, CompositeDisposable disposable, PreferenceUtils preferenceUtils) {
        super(scheduler, disposable, preferenceUtils);
    }
}
