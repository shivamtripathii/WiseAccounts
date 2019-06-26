package com.wiselap.accounts.expense.ExpensePackage;

import android.util.Log;

import com.google.gson.Gson;
import com.wiselap.accounts.base_class.BasePresenterImpl;
import com.wiselap.accounts.constants.ResponseCode;
import com.wiselap.accounts.interfaces.SchedulerProvider;
import com.wiselap.accounts.interfaces.ServiceProvider;
import com.wiselap.accounts.retrofit.WrappedResponse;
import com.wiselap.accounts.utils.PreferenceUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

public class ExpensesPresenter<V extends ExpensesContract.View> extends BasePresenterImpl<V> implements ExpensesContract.Presenter {
    ServiceProvider<ExpenseRequest> serviceProvider;

    @Inject
    public ExpensesPresenter(SchedulerProvider scheduler, CompositeDisposable disposable, ServiceProvider<ExpenseRequest> serviceProvider, PreferenceUtils preferenceUtils) {
        super(scheduler, disposable, preferenceUtils);
        this.serviceProvider=serviceProvider;
    }


    @Override
    public void getExpense(ExpenseMethodModel expenseMethodModel) {
        Log.d("shhh", "onNext: "+new Gson().toJson(expenseMethodModel));
        getView().showLoadingDialog();
        getDisposable().add(serviceProvider.getWrappedService().getExpense(getPreferenceUtils().getShopAgentId(),expenseMethodModel)
                .subscribeOn(getSchedulerProvider().getIoScheduler())
                .observeOn(getSchedulerProvider().getUiScheduler())
                .subscribeWith(new DisposableObserver<WrappedResponse<List<ExpenseReturnModel>>>() {
                    @Override
                    public void onNext(WrappedResponse<List<ExpenseReturnModel>> listWrappedResponse) {
                        Log.d("shhh", "onNext: "+new Gson().toJson(listWrappedResponse));
                        getView().setExpense(listWrappedResponse.getData());
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
    public void deleteExpense(DeleteExpenseMethodModel model) {
        Log.d("Delete, ", new Gson().toJson(model));
        //getView().showLoadingDialog();
        getDisposable().add(serviceProvider.getWrappedService().deleteUsers(model)
                .subscribeOn(getSchedulerProvider().getIoScheduler())
                .observeOn(getSchedulerProvider().getUiScheduler())
                .subscribeWith(new DisposableObserver<WrappedResponse>() {
                    @Override
                    public void onNext(WrappedResponse wrappedResponse) {
                        if (wrappedResponse.getMeta().getId() == ResponseCode.SUCCESS) {
                            getView().refresh();
                        }
                        getView().showMessage(wrappedResponse.getMeta().getMessage());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("Delete, ", e.getMessage().toString());
                    }

                    @Override
                    public void onComplete() {
                        getView().hideLoadingDialog();

                    }
                }));
    }
}
