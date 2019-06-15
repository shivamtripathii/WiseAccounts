package com.wiselap.accounts.expense.AddExpensePackage;

import android.util.Log;

import com.google.gson.Gson;
import com.wiselap.accounts.base_class.BasePresenterImpl;
import com.wiselap.accounts.interfaces.SchedulerProvider;
import com.wiselap.accounts.interfaces.ServiceProvider;
import com.wiselap.accounts.model.ExpenseType;
import com.wiselap.accounts.retrofit.WrappedResponse;
import com.wiselap.accounts.utils.PreferenceUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

public class AddExpensePresenter<V extends AddExpenseContract.view> extends BasePresenterImpl<V> implements AddExpenseContract.presenter {

    ServiceProvider<AddExpenseRequest> serviceProvider;
    private ArrayList<String> expenseTypes=new ArrayList<>();
    private ArrayList<ExpenseType> expenseList=new ArrayList<>();


    @Inject
    public AddExpensePresenter(SchedulerProvider scheduler, CompositeDisposable disposable, PreferenceUtils preferenceUtils, ServiceProvider<AddExpenseRequest> serviceProvider) {
        super(scheduler, disposable, preferenceUtils);
        this.serviceProvider = serviceProvider;
    }


    @Override
    public void addExpense(AddExpenseMethodModel addExpenseMethodModel) {
        //Log.d("shivam", new Gson().toJson(addExpenseMethodModel));
        getView().showLoadingDialog();
        getDisposable().add(serviceProvider.getWrappedService().addExpense(addExpenseMethodModel)
                .subscribeOn(getSchedulerProvider().getIoScheduler())
                .observeOn(getSchedulerProvider().getUiScheduler())
                .subscribeWith(new DisposableObserver<WrappedResponse<AddExpenseReturnModel>>() {
                    @Override
                    public void onNext(WrappedResponse<AddExpenseReturnModel> listWrappedResponse) {
                        //Log.d("shivam", new Gson().toJson(listWrappedResponse));
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
    public long getLoginId() {
        return getPreferenceUtils().getLoginId();

    }

    @Override
    public long getApplicationUserID() {
        return getPreferenceUtils().getApplicationUSerId();
    }

    @Override
    public void setAdapter() {
        getDisposable().add(serviceProvider.getWrappedService().getExpenseTypes(getPreferenceUtils().getAccountingProfile())
                .subscribeOn(getSchedulerProvider().getIoScheduler())
                .observeOn(getSchedulerProvider().getUiScheduler())
                .subscribeWith(new DisposableObserver<WrappedResponse<List<ExpenseType>>>() {

                    @Override
                    public void onNext(WrappedResponse<List<ExpenseType>> listWrappedResponse) {
                        Log.d("spin", "onNext: "+new Gson().toJson(listWrappedResponse));
                        for(ExpenseType row: listWrappedResponse.getData())
                        {
                            expenseTypes.add(row.getExpense_name());
                        }
                        getView().sendExpenseType(expenseTypes);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showMessage("something");
                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    @Override
    public void updateExpense(UpdateExpenseMethodModel model) {
        getView().showLoadingDialog();
        getDisposable().add(serviceProvider.getWrappedService().updateExpense(model)
                .subscribeOn(getSchedulerProvider().getIoScheduler())
                .observeOn(getSchedulerProvider().getUiScheduler())
                .subscribeWith(new DisposableObserver<WrappedResponse<AddExpenseReturnModel>>() {
                    @Override
                    public void onNext(WrappedResponse<AddExpenseReturnModel> listWrappedResponse) {

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
    public long checkExpenseTypeID(String expense_name) {
        for(ExpenseType row : expenseList){
            if(row.getExpense_name().equals(expense_name))
                return row.getId();
        }
        return 0;
    }
}
