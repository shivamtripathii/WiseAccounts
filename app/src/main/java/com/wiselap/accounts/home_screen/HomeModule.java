package com.wiselap.accounts.home_screen;

import com.wiselap.accounts.interfaces.ServiceProvider;
import com.wiselap.accounts.retrofit.RetrofitInstance;

import dagger.Module;
import dagger.Provides;

@Module
public class HomeModule {

    @Provides
    static ServiceProvider<HomeInterface> providesService(){
        return new ServiceProvider<HomeInterface>() {
            @Override
            public HomeInterface getService() {
                return RetrofitInstance.getCustomRetrofitInstance().create(HomeInterface.class);
            }

            @Override
            public HomeInterface getWrappedService() {
                return RetrofitInstance.getRetrofitInstance().create(HomeInterface.class);
            }
        };
    }
}
