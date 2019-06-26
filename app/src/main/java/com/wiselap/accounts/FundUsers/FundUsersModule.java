package com.wiselap.accounts.FundUsers;

import com.wiselap.accounts.interfaces.ServiceProvider;
import com.wiselap.accounts.retrofit.RetrofitInstance;
import com.wiselap.accounts.users.UsersPackage.UserRequest;

import dagger.Module;
import dagger.Provides;

@Module
public class FundUsersModule {
    @Provides
    static ServiceProvider<FundUserRequest> provideSerivce()
    {
        return new ServiceProvider<FundUserRequest>() {
            @Override
            public FundUserRequest getService() {
                return RetrofitInstance.getCustomRetrofitInstance().create(FundUserRequest.class);
            }

            @Override
            public FundUserRequest getWrappedService() {
                return RetrofitInstance.getRetrofitInstance().create(FundUserRequest.class);
            }
        };
    }
}
