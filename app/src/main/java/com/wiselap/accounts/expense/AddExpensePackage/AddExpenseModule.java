package com.wiselap.accounts.expense.AddExpensePackage;

import com.wiselap.accounts.interfaces.ServiceProvider;
import com.wiselap.accounts.retrofit.RetrofitInstance;

import dagger.Module;
import dagger.Provides;

@Module
public class AddExpenseModule {
    @Provides
    static ServiceProvider<AddExpenseRequest> provideSerivce()
    {
        return new ServiceProvider<AddExpenseRequest>() {
            @Override
            public AddExpenseRequest getService() {
                return RetrofitInstance.getCustomRetrofitInstance().create(AddExpenseRequest.class);
            }

            @Override
            public AddExpenseRequest getWrappedService() {
                return RetrofitInstance.getRetrofitInstance().create(AddExpenseRequest.class);
            }
        };
    }
}
