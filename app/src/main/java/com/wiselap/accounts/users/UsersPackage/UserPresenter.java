package com.wiselap.accounts.users.UsersPackage;

import android.util.Log;

import com.google.gson.Gson;
import com.wiselap.accounts.base_class.BasePresenterImpl;
import com.wiselap.accounts.constants.ResponseCode;
import com.wiselap.accounts.interfaces.SchedulerProvider;
import com.wiselap.accounts.interfaces.ServiceProvider;
import com.wiselap.accounts.retrofit.WrappedResponse;
import com.wiselap.accounts.utils.NetUtils;
import com.wiselap.accounts.utils.PreferenceUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

public class UserPresenter<V extends UserContract.view> extends BasePresenterImpl<V> implements UserContract.presenter {
    ServiceProvider<UserRequest> serviceProvider;
    @Inject
    public UserPresenter(SchedulerProvider scheduler, CompositeDisposable disposable, PreferenceUtils preferenceUtils, ServiceProvider<UserRequest> serviceProvider) {
        super(scheduler, disposable, preferenceUtils);
        this.serviceProvider = serviceProvider;
    }



    @Override
    public void getUsers(UserMethodModel userMethodModel) {
        getView().showLoadingDialog();
        Log.d("get, ", new Gson().toJson(userMethodModel));
        getDisposable().add(serviceProvider.getWrappedService().getUsers(userMethodModel)
                .subscribeOn(getSchedulerProvider().getIoScheduler())
                .observeOn(getSchedulerProvider().getUiScheduler())
                .subscribeWith(new DisposableObserver<WrappedResponse<List<UserReturnModel>>>() {
                    @Override
                    public void onNext(WrappedResponse<List<UserReturnModel>> listWrappedResponse) {
                        Log.d("get, ", new Gson().toJson(listWrappedResponse));
                        getView().setUsers(listWrappedResponse.getData());
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

    @Override
    public void deleteUser(DeleteUserMethodModel model) {
        Log.d("Delete, ", new Gson().toJson(model));
        getView().showLoadingDialog();
        getDisposable().add(serviceProvider.getWrappedService().deleteUsers(model)
                .subscribeOn(getSchedulerProvider().getIoScheduler())
                .observeOn(getSchedulerProvider().getUiScheduler())
                .subscribeWith(new DisposableObserver<WrappedResponse>() {
                    @Override
                    public void onNext(WrappedResponse wrappedResponse) {
                        if (wrappedResponse.getMeta().getId() == ResponseCode.SUCCESS) {
                            getView().refresh();
                        }
                        Log.d("Delete, ", new Gson().toJson(wrappedResponse));
                        getView().showMessage(wrappedResponse.getMeta().getMessage());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("DeleteE, ", new Gson().toJson(model));
                        getView().showMessage(NetUtils.getMessage(e));
                        getView().hideLoadingDialog();
                    }

                    @Override
                    public void onComplete() {
                        getView().hideLoadingDialog();
                    }
                }));
    }

    @Override
    public long getApplicationUserId() {
        return getPreferenceUtils().getApplicationUSerId();
    }

}

