package com.wiselap.accounts.SignIn;

import com.wiselap.accounts.model.Accounts;
import com.wiselap.accounts.model.LoginId;
import com.wiselap.accounts.model.AuthenticationUsingEmail;
import com.wiselap.accounts.retrofit.WrappedResponse;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface EmailRequest {

    @POST("checkAuthenticationUsingEmail")
    Observable<WrappedResponse<EmailData>> getEmail(@Body AuthenticationUsingEmail authenticationUsingEmail);

    @POST("accounts")
    Observable<ArrayList<Accounts>> getAccounts(@Body LoginId loginId);
}