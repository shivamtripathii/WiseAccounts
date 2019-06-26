package com.wiselap.accounts.Office;

import android.util.Log;

import com.google.gson.Gson;
import com.wiselap.accounts.ExpenseType.AddExpenseType.ApiInterface;
import com.wiselap.accounts.Personal.PersonalService;
import com.wiselap.accounts.base_class.BasePresenterImpl;
import com.wiselap.accounts.constants.ResponseCode;
import com.wiselap.accounts.interfaces.SchedulerProvider;
import com.wiselap.accounts.interfaces.ServiceProvider;
import com.wiselap.accounts.model.AccountModel;
import com.wiselap.accounts.model.EntityModel;
import com.wiselap.accounts.model.OfficeEntity;
 import com.wiselap.accounts.retrofit.WrappedResponse;
import com.wiselap.accounts.utils.PreferenceUtils;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

public class OfficePresenter<V extends OfficeContract.View> extends BasePresenterImpl<V> implements OfficeContract.Presenter {

    private ServiceProvider<PersonalService> serviceProvider;
    private final String TAG = OfficePresenter.class.getSimpleName();
    @Inject
    public OfficePresenter(SchedulerProvider scheduler, ServiceProvider<PersonalService> serviceProvider, CompositeDisposable disposable, PreferenceUtils preferenceUtils) {
        super(scheduler, disposable, preferenceUtils);
        this.serviceProvider = serviceProvider;
    }

    @Override
    public void sendData(String office, String contact, String owner, String address) {

        OfficeEntity officeEntity = new OfficeEntity(office, contact, owner, address);
        officeEntity.setApplicationUserId(getPreferenceUtils().getApplicationUSerId());
        ///Log.d(TAG, "hjsgfahsgf" + new Gson().toJson(officeEntity));
        getView().showLoadingDialog();
        getDisposable().add(serviceProvider.getWrappedService().sendOfficeEntity(officeEntity)
                .subscribeOn(getSchedulerProvider().getIoScheduler())
                .observeOn(getSchedulerProvider().getUiScheduler())
                .subscribeWith(new DisposableObserver<WrappedResponse<EntityModel>>() {
                    @Override
                    public void onNext(WrappedResponse<EntityModel> entityModelWrappedResponse) {
                        //Log.d(TAG, "hjsgfahsgf" + new Gson().toJson(entityModelWrappedResponse));
                        if(entityModelWrappedResponse.getMeta().getId() == ResponseCode.SUCCESS) {
                            getView().intentToHome();
                            getView().showMessage("Entity has been added");
                            getPreferenceUtils().saveAccountingProfileId(entityModelWrappedResponse.getData().getAccountingProfileId());
                            getPreferenceUtils().saveShopAgentId(entityModelWrappedResponse.getData().getShopAgentId());
                            getPreferenceUtils().saveApplicationType(entityModelWrappedResponse.getData().getApplicationTypeName());
                        }
                        else
                            getView().showMessage("Something went wrong");
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().hideLoadingDialog();
                        getView().showMessage("Something went wrong");
                    }

                    @Override
                    public void onComplete() {
                        getView().hideLoadingDialog();
                    }
                }));
    }

    @Override
    public void updateData(AccountModel accountModel, String office, String contact, String owner, String address) {
        EntityModel entityModel = new EntityModel(getPreferenceUtils().getAccountingProfile(),
                office, null, contact, owner, address, accountModel.getProfileEntity(),
                getPreferenceUtils().getApplicationUSerId(), getPreferenceUtils().getShopAgentId(), null);

        getView().showLoadingDialog();
        Log.d(TAG, "getAccountDetails: "+ new Gson().toJson(entityModel));
        getDisposable().add(serviceProvider.getWrappedService().updatePersonalEntity(entityModel)
                .subscribeOn(getSchedulerProvider().getIoScheduler())
                .observeOn(getSchedulerProvider().getUiScheduler())
                .subscribeWith(new DisposableObserver<WrappedResponse>() {
                    @Override
                    public void onNext(WrappedResponse wrappedResponse) {
                        if(wrappedResponse.getMeta().getId() == ResponseCode.SUCCESS)
                            getView().intentToHome();
                        else
                            getView().showMessage(wrappedResponse.getMeta().getMessage());
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

    @Override
    public String getEmailId() {
        return getPreferenceUtils().getEmailId();
    }
}
