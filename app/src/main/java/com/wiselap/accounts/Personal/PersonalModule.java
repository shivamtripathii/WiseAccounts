package com.wiselap.accounts.Personal;

import com.wiselap.accounts.interfaces.ServiceProvider;
import com.wiselap.accounts.retrofit.RetrofitInstance;

import dagger.Module;
import dagger.Provides;

@Module
public class PersonalModule {

    @Provides
    static ServiceProvider<PersonalService> providesService(){
        return new ServiceProvider<PersonalService>() {
            @Override
            public PersonalService getService() {
                return RetrofitInstance.getCustomRetrofitInstance().create(PersonalService.class);
            }

            @Override
            public PersonalService getWrappedService() {
                return RetrofitInstance.getRetrofitInstance().create(PersonalService.class);            }
        };
    }
}

