package com.wiselap.accounts.ExpenseType;

import android.util.Log;

import com.google.gson.Gson;
import com.wiselap.accounts.RequestCode;
import com.wiselap.accounts.base_class.BasePresenterImpl;
import com.wiselap.accounts.constants.ResponseCode;
import com.wiselap.accounts.interfaces.SchedulerProvider;
import com.wiselap.accounts.interfaces.ServiceProvider;
import com.wiselap.accounts.model.ExpenseType;
import com.wiselap.accounts.retrofit.WrappedResponse;
import com.wiselap.accounts.utils.NetUtils;
import com.wiselap.accounts.utils.PreferenceUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

public class ExpenseTypePresenter<V extends ExpenseTypeContract.View> extends BasePresenterImpl<V> implements ExpenseTypeContract.Presenter {

    private ServiceProvider<GetExpenseTypes> serviceProvider;
    private final String TAG = ExpenseTypePresenter.class.getSimpleName();

    @Inject
    public ExpenseTypePresenter(SchedulerProvider scheduler, CompositeDisposable disposable, PreferenceUtils preferenceUtils, ServiceProvider<GetExpenseTypes> serviceProvider) {
        super(scheduler, disposable, preferenceUtils);
        this.serviceProvider = serviceProvider;
    }

    @Override
    public void setAdapter() {
        getView().showLoadingDialog();
        getDisposable().add(serviceProvider.getWrappedService().getExpenseTypes(getPreferenceUtils().getAccountingProfile())
        .subscribeOn(getSchedulerProvider().getIoScheduler())
        .observeOn(getSchedulerProvider().getUiScheduler())
        .subscribeWith(new DisposableObserver<WrappedResponse<List<ExpenseType>>>() {
            @Override
            public void onNext(WrappedResponse<List<ExpenseType>> listWrappedResponse) {
                getView().showLoadingDialog();
                Log.d(TAG, "hjsgfkja" +" USER "+getPreferenceUtils().getAccountingProfile()+" "+ new Gson().toJson(listWrappedResponse));
                if(listWrappedResponse.getMeta().getId() == RequestCode.SUCCESS)
                    getView().createAdapter(listWrappedResponse.getData());
                else
                    getView().showMessage(listWrappedResponse.getMeta().getMessage());
            }

            @Override
            public void onError(Throwable e) {
                getView().hideLoadingDialog();
                Log.e(TAG, e.getMessage());
                getView().showMessage("Something went wrong");
            }

            @Override
            public void onComplete() {
                getView().hideLoadingDialog();
            }
        }));

    }

    @Override
    public void deleteExpenseType(ExpenseType expenseType) {
        getView().showLoadingDialog();
        ExpenseType expenseType1 = new ExpenseType(expenseType.getId(), null, null,null);
        expenseType1.setAccountingProfileId(null);

        Log.d(TAG, "hjsgfkja"+new Gson().toJson(expenseType));

        getDisposable().add(serviceProvider.getWrappedService().deleteExpenseTypes(expenseType1)
                .subscribeOn(getSchedulerProvider().getIoScheduler())
        .observeOn(getSchedulerProvider().getUiScheduler())
        .subscribeWith(new DisposableObserver<WrappedResponse>() {
            @Override
            public void onNext(WrappedResponse wrappedResponse) {
                Log.d(TAG, "hjsgfkja"+new Gson().toJson(wrappedResponse));

                 if(wrappedResponse.getMeta().getId() == ResponseCode.SUCCESS)
                {
                    Log.d(TAG, "hjsgfkja"+new Gson().toJson(wrappedResponse));
                    getView().removeItemFromList();
                }

                getView().showMessage(wrappedResponse.getMeta().getMessage());
            }

            @Override
            public void onError(Throwable e) {
                getView().hideLoadingDialog();
                getView().showMessage(NetUtils.getMessage(e));
            }

            @Override
            public void onComplete() {
                getView().hideLoadingDialog();
            }
        }));
    }



}
