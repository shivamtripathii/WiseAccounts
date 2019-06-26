package com.wiselap.accounts.Personal;

import com.wiselap.accounts.model.AccountModel;
import com.wiselap.accounts.model.EntityModel;
import com.wiselap.accounts.model.OfficeEntity;
 import com.wiselap.accounts.retrofit.WrappedResponse;
import com.wiselap.accounts.utils.URLS;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface PersonalService {

    @POST(URLS.addAccountingProfile)
    Observable<WrappedResponse<EntityModel>> sendPersonalEntity(@Body PersonalEntity personalEntity);

    @POST(URLS.addAccountingProfile)
    Observable<WrappedResponse<EntityModel>> sendOfficeEntity(@Body OfficeEntity officeEntity);

    @PUT(URLS.updateAccountingProfile)
    Observable<WrappedResponse> updatePersonalEntity(@Body EntityModel entityModel);
}
