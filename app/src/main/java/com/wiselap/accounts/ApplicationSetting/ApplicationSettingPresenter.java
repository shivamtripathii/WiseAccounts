package com.wiselap.accounts.ApplicationSetting;

import com.wiselap.accounts.base_class.BasePresenterImpl;
import com.wiselap.accounts.interfaces.SchedulerProvider;
import com.wiselap.accounts.utils.PreferenceUtils;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class ApplicationSettingPresenter<V extends ApplicationSettingContract.View> extends BasePresenterImpl<V> implements ApplicationSettingContract.Presenter {
    @Inject
    public ApplicationSettingPresenter(SchedulerProvider scheduler, CompositeDisposable disposable, PreferenceUtils preferenceUtils) {
        super(scheduler, disposable, preferenceUtils);
    }

    @Override
    public void saveApproval(int i) {
        getView().intentToConfiguration();
    }
}
