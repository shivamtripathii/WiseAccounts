package com.wiselap.accounts.FundTransferMethod;

import com.wiselap.accounts.retrofit.WrappedResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface FundMethodRequest {
    @POST("fundTransfer")
    Observable<WrappedResponse> doPaymentBank(@Body BankModel bankModel);

    @POST("fundTransfer")
    Observable<WrappedResponse> doPaymentUpi(@Body UPIModel upiModel);

    @POST("fundTransfer")
    Observable<WrappedResponse> doPaymentCash(@Body CashModel cashModel);
}
