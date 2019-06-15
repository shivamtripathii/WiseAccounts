package com.wiselap.accounts.expense.AddExpensePackage;

import com.wiselap.accounts.model.ExpenseType;
import com.wiselap.accounts.retrofit.WrappedResponse;
import com.wiselap.accounts.utils.URLS;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AddExpenseRequest {

    @POST("addExpense")
    Observable<WrappedResponse<AddExpenseReturnModel>> addExpense(@Body AddExpenseMethodModel addExpenseMethodModel);

    @GET(URLS.getExpenseTypes+"/{accountingProfileId}")
    Observable<WrappedResponse<List<ExpenseType>>> getExpenseTypes(@Path("accountingProfileId") long applicationUserId);

    @PUT("updateExpense")
    Observable<WrappedResponse<AddExpenseReturnModel>> updateExpense(@Body UpdateExpenseMethodModel model);
}
