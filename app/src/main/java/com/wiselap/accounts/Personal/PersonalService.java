package com.wiselap.accounts.Personal;

import com.wiselap.accounts.model.EntityModel;
import com.wiselap.accounts.model.OfficeEntity;
 import com.wiselap.accounts.retrofit.WrappedResponse;
import com.wiselap.accounts.utils.URLS;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PersonalService {

    @POST(URLS.addAccountingProfile)
    Observable<WrappedResponse<EntityModel>> sendPersonalEntity(@Body PersonalEntity personalEntity);

    @POST(URLS.addAccountingProfile)
    Observable<WrappedResponse<EntityModel>> sendOfficeEntity(@Body OfficeEntity officeEntity);
}
