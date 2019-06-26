package com.wiselap.accounts.home_screen;

import com.wiselap.accounts.model.AccountModel;
import com.wiselap.accounts.retrofit.WrappedResponse;
import com.wiselap.accounts.utils.URLS;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface HomeInterface {

    @POST(URLS.accountInfo)
    Observable<WrappedResponse<AccountModel>> getAccountInfo(@Body GetAccountModel getAccountModel);
}
