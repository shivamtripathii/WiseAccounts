package com.wiselap.accounts.users.AddUsersPackage;

import com.wiselap.accounts.interfaces.ServiceProvider;
import com.wiselap.accounts.retrofit.RetrofitInstance;

import dagger.Module;
import dagger.Provides;

@Module
public class AddUserModule {
    @Provides
    static ServiceProvider<AddUserRequest> provideSerivce()
    {
        return new ServiceProvider<AddUserRequest>() {
            @Override
            public AddUserRequest getService() {
                return RetrofitInstance.getCustomRetrofitInstance().create(AddUserRequest.class);
            }

            @Override
            public AddUserRequest getWrappedService() {
                return RetrofitInstance.getRetrofitInstance().create(AddUserRequest.class);
            }
        };
    }
}
