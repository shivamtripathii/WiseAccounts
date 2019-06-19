package com.wiselap.accounts.utils;

import android.content.SharedPreferences;
import android.util.Log;

import com.wiselap.accounts.constants.AppConstants;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.wiselap.accounts.SignIn.EmailData;


@Singleton
public class PreferenceUtils {

    private SharedPreferences sharedPreferences;

    @Inject
    public PreferenceUtils(SharedPreferences sharedPreferences){
        this.sharedPreferences = sharedPreferences;
    }

    public void saveLoginInfo(EmailData data, String email) {
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putLong(AppConstants.applicationUserId,data.getApplicationUserId());
        editor.putLong(AppConstants.loginId,data.getLoginId());
        editor.putString(AppConstants.uniqueIdentityField,email);
        editor.commit();
    }

    public void saveAccountingProfileId(long accountingProfileId){
        //Log.d("pakaskj","djfksdjfkldsjfk accountingProfileId "+accountingProfileId);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(AppConstants.accountingProfileId, accountingProfileId);
        editor.commit();
    }
    public void saveShopAgentId(long shopAgentId){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(AppConstants.shopAgentId, shopAgentId);
        editor.commit();
    }
    public String getEmailId(){
        return sharedPreferences.getString(AppConstants.uniqueIdentityField,"");
    }



    public long getApplicationUSerId( ){
        return sharedPreferences.getLong(AppConstants.applicationUserId, 0);
    }

    public long getLoginId( ){
        return sharedPreferences.getLong(AppConstants.loginId, 0);
    }

    public long getShopAgentId( ){
        return sharedPreferences.getLong(AppConstants.shopAgentId, 0);
    }


    public long getAccountingProfile(){
        return sharedPreferences.getLong(AppConstants.accountingProfileId,0);
    }

    public void clearAll() {
        Log.d("pakaskj","djfksdjfkldsjfk accountingProfileId clearAll");

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(AppConstants.applicationUserId);
        editor.remove(AppConstants.loginId);
        editor.remove(AppConstants.uniqueIdentityField);
        editor.remove(AppConstants.accountingProfileId);
        editor.remove(AppConstants.applicationType);
        editor.commit();
    }

    public void saveApplicationType(String applicationTypeName) {
        sharedPreferences.edit().putString(AppConstants.applicationType,applicationTypeName).commit();
    }

    public String getApplicationType(){
        return sharedPreferences.getString(AppConstants.applicationType,"");
    }
    /*
   public void saveLoginData(long applicationjUserId){
      sharedPreferences.edit().putLong(AppConstants.applicationUserId,applicationjUserId).commit();
   }

   public long getApplicationUserId(){
      return sharedPreferences.getLong(AppConstants.applicationUserId,0);
   }*/



}
