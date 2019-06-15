package com.wiselap.accounts.users.UsersPackage;

import com.wiselap.accounts.retrofit.WrappedResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.HTTP;
import retrofit2.http.POST;

public interface UserRequest {

    @POST("accountingUsers")
    Observable<WrappedResponse<List<UserReturnModel>>> getUsers(@Body UserMethodModel userMethodModel);

    @HTTP(method = "DELETE", path="deleteAccountingUser", hasBody = true)
    Observable<WrappedResponse> deleteUsers(@Body DeleteUserMethodModel model);
}
