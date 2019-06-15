package com.wiselap.accounts.ExpenseType;

 import com.wiselap.accounts.model.ExpenseType;
import com.wiselap.accounts.retrofit.WrappedResponse;
 import com.wiselap.accounts.utils.URLS;

 import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Path;

public interface GetExpenseTypes {

    @GET(URLS.getExpenseTypes+"/{accountingProfileId}")
    Observable<WrappedResponse<List<ExpenseType>>> getExpenseTypes(@Path("accountingProfileId") long accountingProfileId);

    @HTTP(method = "DELETE", path = URLS.deleteExpenseTypes, hasBody = true)
    Observable<WrappedResponse> deleteExpenseTypes(@Body ExpenseType expenseType);
}
