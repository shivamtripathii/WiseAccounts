package com.wiselap.accounts.SignIn;

import android.util.Log;

import com.google.gson.Gson;
import com.wiselap.accounts.base_class.BasePresenterImpl;
import com.wiselap.accounts.constants.ResponseCode;
import com.wiselap.accounts.interfaces.SchedulerProvider;
import com.wiselap.accounts.interfaces.ServiceProvider;
import com.wiselap.accounts.model.Accounts;
import com.wiselap.accounts.model.ApplicationUserID;
 import com.wiselap.accounts.model.AuthenticationUsingEmail;
import com.wiselap.accounts.retrofit.WrappedResponse;
import com.wiselap.accounts.utils.NetUtils;
import com.wiselap.accounts.utils.PreferenceUtils;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

public class LoginPresenter<V extends LoginContract.view> extends BasePresenterImpl<V> implements LoginContract.presenter {
    ServiceProvider<EmailRequest> serviceProvider;

    @Inject
    public LoginPresenter(SchedulerProvider scheduler, CompositeDisposable disposable, PreferenceUtils preferenceUtils, ServiceProvider<EmailRequest> serviceProvider) {
        super(scheduler, disposable, preferenceUtils);
        this.serviceProvider = serviceProvider;
    }


    @Override
    public void sendEmailId(AuthenticationUsingEmail authenticationUsingEmail) {
        Log.d("fd", "authenticationUsingEmail " + new Gson().toJson(authenticationUsingEmail));
        getView().showLoadingDialog();
        getDisposable().add(serviceProvider.getWrappedService().getEmail(authenticationUsingEmail)
                .subscribeOn(getSchedulerProvider().getIoScheduler())
                .observeOn(getSchedulerProvider().getUiScheduler())
                .subscribeWith(new DisposableObserver<WrappedResponse<EmailData>>() {
                    @Override
                    public void onNext(WrappedResponse<EmailData> emailDataWrappedResponse) {
                       // getView().showMessage(" " + new Gson().toJson(emailDataWrappedResponse));
                        Log.d("Shivam", " " + new Gson().toJson(emailDataWrappedResponse));


                        /**
                         * treating success as new user
                         * error : account already exists
                         * */
                        if (emailDataWrappedResponse.getMeta().getId() == ResponseCode.SUCCESS){
                            getPreferenceUtils().saveLoginInfo(emailDataWrappedResponse.getData(),authenticationUsingEmail.getEmail());
                            getView().intentToSelectEntitiy();
                        }else if(emailDataWrappedResponse.getMeta().getId() == ResponseCode.ERROR){
                            getPreferenceUtils().saveLoginInfo(emailDataWrappedResponse.getData(),authenticationUsingEmail.getEmail());
                            getAccounts(new ApplicationUserID(emailDataWrappedResponse.getData().getApplicationUserId()));
                        }

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
    public void getAccounts(ApplicationUserID applicationUserID) {
        Log.d("832872", "hsjgfahjg" + new Gson().toJson(applicationUserID));
        getDisposable().add(serviceProvider.getService().getAccounts(applicationUserID)
                .subscribeOn(getSchedulerProvider().getIoScheduler())
                .observeOn(getSchedulerProvider().getUiScheduler())
                .subscribeWith(new DisposableObserver<ArrayList<Accounts>>() {
                    @Override
                    public void onNext(ArrayList<Accounts> listWrappedResponse) {
                        Log.d("832872", "hsjgfasfdshjg" + new Gson().toJson(listWrappedResponse));
                        if (listWrappedResponse.isEmpty()){
                            getView().intentToSelectEntitiy();
                        }else{
                            getView().intentToAccounts(listWrappedResponse);
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        getView().showMessage(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

}
