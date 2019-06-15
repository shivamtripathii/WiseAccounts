package com.wiselap.accounts.users.AddUsersPackage;

import com.wiselap.accounts.retrofit.WrappedResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface AddUserRequest {

    @POST("addAccountingUser")
    Observable<WrappedResponse> addUsers(@Body AddUserMethodModel addUserMethodModel);

    @PUT("updateAccountingUser")
    Observable<WrappedResponse> updateUsers(@Body UpdateUserMethodModel model);


}
