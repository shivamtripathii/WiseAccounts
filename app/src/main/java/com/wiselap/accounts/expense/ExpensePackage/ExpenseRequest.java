package com.wiselap.accounts.expense.ExpensePackage;

import com.wiselap.accounts.retrofit.WrappedResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ExpenseRequest {
    @POST("getExpense/{shopAgentId}")
    Observable<WrappedResponse<List<ExpenseReturnModel>>> getExpense(@Path("shopAgentId") long shopAgentId,
                                                                     @Body ExpenseMethodModel expenseMethodModel);

    @HTTP(method = "DELETE", path="deleteExpense", hasBody = true)
    Observable<WrappedResponse> deleteUsers(@Body DeleteExpenseMethodModel model);
}
