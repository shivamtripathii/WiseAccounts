package com.wiselap.accounts.FundUsers;

import com.wiselap.accounts.model.ShopAgentId;
import com.wiselap.accounts.retrofit.WrappedResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface FundUserRequest {
    @POST("usersAdvance")
    Observable<WrappedResponse<List<FundUsersReturnModel>>> getFundUsers(@Body ShopAgentId shopAgentId);
}
