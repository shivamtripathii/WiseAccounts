package com.wiselap.accounts.home_screen;

import android.util.Log;

import com.google.gson.Gson;
import com.wiselap.accounts.base_class.BasePresenterImpl;
import com.wiselap.accounts.constants.AppConstants;
import com.wiselap.accounts.constants.ResponseCode;
import com.wiselap.accounts.interfaces.SchedulerProvider;
import com.wiselap.accounts.interfaces.ServiceProvider;
import com.wiselap.accounts.model.AccountModel;
import com.wiselap.accounts.retrofit.WrappedResponse;
import com.wiselap.accounts.utils.PreferenceUtils;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

public class HomePresenter<V extends HomeContract.View> extends BasePresenterImpl<V> implements HomeContract.Presenter {

    private static final String TAG = HomePresenter.class.getSimpleName();
    private ServiceProvider<HomeInterface> serviceProvider;

    @Inject
    public HomePresenter(SchedulerProvider scheduler, ServiceProvider<HomeInterface> serviceProvider, CompositeDisposable disposable, PreferenceUtils preferenceUtils) {
        super(scheduler, disposable, preferenceUtils);
        this.serviceProvider = serviceProvider;
    }


    @Override
    public void getAccountDetails() {
        getView().showLoadingDialog();
        GetAccountModel getAccountModel = new GetAccountModel(getPreferenceUtils().getShopAgentId());

        Log.d(TAG, "getAccountDetails: "+new Gson().toJson(getAccountModel));
        getDisposable().add(serviceProvider.getWrappedService().getAccountInfo(getAccountModel)
        .subscribeOn(getSchedulerProvider().getIoScheduler())
        .observeOn(getSchedulerProvider().getUiScheduler())
        .subscribeWith(new DisposableObserver<WrappedResponse<AccountModel>>() {
            @Override
            public void onNext(WrappedResponse<AccountModel> accountModelWrappedResponse) {
                Log.d(TAG, "getAccountDetails: "+new Gson().toJson(accountModelWrappedResponse));
                if(accountModelWrappedResponse.getMeta().getId() == ResponseCode.SUCCESS){
                    if(accountModelWrappedResponse.getData().getProfileEntity().equals(AppConstants.office))
                        getView().intentToOffice(accountModelWrappedResponse.getData());
                    else if(accountModelWrappedResponse.getData().getProfileEntity().equals(AppConstants.personal)){
                        getView().intentToPersonal(accountModelWrappedResponse.getData());
                    }

                }
                else
                    getView().showMessage(accountModelWrappedResponse.getMeta().getMessage());
            }

            @Override
            public void onError(Throwable e) {
                getView().hideLoadingDialog();
                getView().showMessage(e.getMessage());
            }

            @Override
            public void onComplete() {
                getView().hideLoadingDialog();
            }
        }));
    }
}
