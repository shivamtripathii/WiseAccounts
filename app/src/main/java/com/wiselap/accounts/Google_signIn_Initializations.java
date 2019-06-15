package com.wiselap.accounts;

import android.content.Context;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;

public class Google_signIn_Initializations {
    private static GoogleSignInClient mGoogleSignInClient;
    private static GoogleApiClient googleApiClient;
    private static  Google_signIn_Initializations googleSignIn;

    private Google_signIn_Initializations(Context context) {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(context, gso);
        googleApiClient = new GoogleApiClient.Builder(context)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        googleApiClient.connect();
    }


    public static Google_signIn_Initializations getInstance(Context context){
        if (googleSignIn == null){
            googleSignIn = new Google_signIn_Initializations(context);
        }
        return googleSignIn;
    }

    public GoogleApiClient getGoogleApiClient() {
        return googleApiClient;
    }

    public GoogleSignInClient getmGoogleSignInClient() {
        return mGoogleSignInClient;
    }
}
