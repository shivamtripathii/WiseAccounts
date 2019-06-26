package com.wiselap.accounts.users.AddUsersPackage;

import android.util.Log;

import com.google.gson.Gson;
import com.wiselap.accounts.base_class.BasePresenterImpl;
import com.wiselap.accounts.interfaces.SchedulerProvider;
import com.wiselap.accounts.interfaces.ServiceProvider;
import com.wiselap.accounts.retrofit.WrappedResponse;
import com.wiselap.accounts.utils.NetUtils;
import com.wiselap.accounts.utils.PreferenceUtils;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

public class AddUserPresenter<V extends AddUserContract.view> extends BasePresenterImpl<V> implements AddUserContract.presenter {

    private ServiceProvider<AddUserRequest> serviceProvider;

    @Inject
    public AddUserPresenter(SchedulerProvider scheduler, CompositeDisposable disposable, PreferenceUtils preferenceUtils, ServiceProvider<AddUserRequest> serviceProvider) {
        super(scheduler, disposable, preferenceUtils);
        this.serviceProvider = serviceProvider;
    }

    @Override
    public void getMeta(AddUserMethodModel addUserMethodModel) {
        getView().showLoadingDialog();
        Log.d("Update, ", new Gson().toJson(addUserMethodModel));
        getDisposable().add(serviceProvider.getWrappedService().addUsers(addUserMethodModel)
                .subscribeOn(getSchedulerProvider().getIoScheduler())
                .observeOn(getSchedulerProvider().getUiScheduler())
                .subscribeWith(new DisposableObserver<WrappedResponse>() {

                    @Override
                    public void onNext(WrappedResponse wrappedResponse) {
                        Log.d("Update, ", new Gson().toJson(wrappedResponse));
                        getView().sendMeta(wrappedResponse.getMeta().getId());
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
    public void updateUser(UpdateUserMethodModel model) {
        Log.d("Update, ", new Gson().toJson(model));
        getDisposable().add(serviceProvider.getWrappedService().updateUsers(model)
                .subscribeOn(getSchedulerProvider().getIoScheduler())
                .observeOn(getSchedulerProvider().getUiScheduler())
                .subscribeWith(new DisposableObserver<WrappedResponse>() {
                    @Override
                    public void onNext(WrappedResponse wrappedResponse) {
                        Log.d("Update, ", new Gson().toJson(wrappedResponse));
                        getView().sendMeta(wrappedResponse.getMeta().getId());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("Update, ", new Gson().toJson(model));
                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }
}
