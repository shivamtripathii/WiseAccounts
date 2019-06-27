package com.wiselap.accounts.FundTransferMethod;

import android.util.Log;

import com.google.gson.Gson;
import com.wiselap.accounts.FundUsers.FundUserRequest;
import com.wiselap.accounts.FundUsers.FundUsersReturnModel;
import com.wiselap.accounts.base_class.BasePresenterImpl;
import com.wiselap.accounts.interfaces.SchedulerProvider;
import com.wiselap.accounts.interfaces.ServiceProvider;
import com.wiselap.accounts.retrofit.WrappedResponse;
import com.wiselap.accounts.utils.PreferenceUtils;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

public class FundMethodPresenter<V extends FundMethodContract.View> extends BasePresenterImpl<V> implements FundMethodContract.Presenter {
    ServiceProvider<FundMethodRequest> serviceProvider;
    @Inject
    public FundMethodPresenter(SchedulerProvider scheduler, CompositeDisposable disposable, PreferenceUtils preferenceUtils,ServiceProvider<FundMethodRequest> serviceProvider) {
        super(scheduler, disposable, preferenceUtils);
        this.serviceProvider=serviceProvider;
    }

    @Override
    public void doPaymentBank(BankModel bankModel) {
        getView().showLoadingDialog();
        Log.d("get, ", new Gson().toJson(bankModel));
        getDisposable().add(serviceProvider.getWrappedService().doPaymentBank(bankModel)
                .subscribeOn(getSchedulerProvider().getIoScheduler())
                .observeOn(getSchedulerProvider().getUiScheduler())
                .subscribeWith(new DisposableObserver<WrappedResponse>() {
                    @Override
                    public void onNext(WrappedResponse listWrappedResponse) {
                        Log.d("get, ", new Gson().toJson(listWrappedResponse));
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().hideLoadingDialog();
                    }

                    @Override
                    public void onComplete() {
                        getView().hideLoadingDialog();
                    }
                }));
    }

    @Override
    public void doPaymentBank(CashModel cashModel) {
        getDisposable().add(serviceProvider.getWrappedService().doPaymentCash(cashModel)
                .subscribeOn(getSchedulerProvider().getIoScheduler())
                .observeOn(getSchedulerProvider().getUiScheduler())
                .subscribeWith(new DisposableObserver<WrappedResponse>() {
                    @Override
                    public void onNext(WrappedResponse listWrappedResponse) {
                        Log.d("get, ", new Gson().toJson(listWrappedResponse));
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().hideLoadingDialog();
                    }

                    @Override
                    public void onComplete() {
                        getView().hideLoadingDialog();
                    }
                }));
    }

    @Override
    public void doPaymentBank(UPIModel upiModel) {
        getDisposable().add(serviceProvider.getWrappedService().doPaymentUpi(upiModel)
                .subscribeOn(getSchedulerProvider().getIoScheduler())
                .observeOn(getSchedulerProvider().getUiScheduler())
                .subscribeWith(new DisposableObserver<WrappedResponse>() {
                    @Override
                    public void onNext(WrappedResponse listWrappedResponse) {
                        Log.d("get, ", new Gson().toJson(listWrappedResponse));
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().hideLoadingDialog();
                    }

                    @Override
                    public void onComplete() {
                        getView().hideLoadingDialog();
                    }
                }));
    }
}
