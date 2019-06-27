package com.wiselap.accounts.FundTransferMethod;

import com.wiselap.accounts.interfaces.ServiceProvider;
import com.wiselap.accounts.retrofit.RetrofitInstance;

import dagger.Module;
import dagger.Provides;

@Module
public class FundMethodModule {
    @Provides
    static ServiceProvider<FundMethodRequest> provideSerivce()
    {
        return new ServiceProvider<FundMethodRequest>() {
            @Override
            public FundMethodRequest getService() {
                return RetrofitInstance.getCustomRetrofitInstance().create(FundMethodRequest.class);
            }

            @Override
            public FundMethodRequest getWrappedService() {
                return RetrofitInstance.getRetrofitInstance().create(FundMethodRequest.class);
            }
        };
    }
}
