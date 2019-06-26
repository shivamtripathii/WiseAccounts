package com.wiselap.accounts.FundUsers;

import android.util.Log;

import com.google.gson.Gson;
import com.wiselap.accounts.base_class.BasePresenterImpl;
import com.wiselap.accounts.interfaces.SchedulerProvider;
import com.wiselap.accounts.interfaces.ServiceProvider;
import com.wiselap.accounts.model.ShopAgentId;
import com.wiselap.accounts.retrofit.WrappedResponse;
import com.wiselap.accounts.users.UsersPackage.UserReturnModel;
import com.wiselap.accounts.utils.NetUtils;
import com.wiselap.accounts.utils.PreferenceUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

public class FundUsersPresenter<V extends FundUsersContract.View> extends BasePresenterImpl<V> implements FundUsersContract.Presenter {
    ServiceProvider<FundUserRequest> serviceProvider;
    @Inject
    public FundUsersPresenter(SchedulerProvider scheduler, CompositeDisposable disposable, PreferenceUtils preferenceUtils,ServiceProvider<FundUserRequest> serviceProvider) {
        super(scheduler, disposable, preferenceUtils);
        this.serviceProvider=serviceProvider;
    }

    @Override
    public void getFundUser(ShopAgentId shopAgentId) {
        getView().showLoadingDialog();
        Log.d("get, ", new Gson().toJson(shopAgentId));
        getDisposable().add(serviceProvider.getWrappedService().getFundUsers(shopAgentId)
                .subscribeOn(getSchedulerProvider().getIoScheduler())
                .observeOn(getSchedulerProvider().getUiScheduler())
                .subscribeWith(new DisposableObserver<WrappedResponse<List<FundUsersReturnModel>>>() {
                    @Override
                    public void onNext(WrappedResponse<List<FundUsersReturnModel>> listWrappedResponse) {
                        Log.d("get, ", new Gson().toJson(listWrappedResponse));
                        getView().setFundUsers(listWrappedResponse.getData());
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showMessage(NetUtils.getMessage(e));
                        getView().hideLoadingDialog();
                    }

                    @Override
                    public void onComplete() {
                        getView().hideLoadingDialog();
                    }
                }));
    }
}
