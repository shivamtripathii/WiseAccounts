package com.wiselap.accounts.utils;

import java.net.NoRouteToHostException;
import java.net.SocketTimeoutException;
import com.wiselap.accounts.retrofit.WrappedError;

public class NetUtils {

        public static String getMessage(Throwable e){
            e.printStackTrace();
            if (e instanceof SocketTimeoutException){
                return "Connection time out";
            }else if(e instanceof WrappedError){
                return ((WrappedError) e).getRuntimeError();
            }else if(e instanceof NoRouteToHostException){
                return "We are under maintenance.Please try again after sometime.";
            }else if(e instanceof java.net.ConnectException){
                return "Poor Connection.Please check your network connection.";
            }else if(e instanceof retrofit2.adapter.rxjava2.HttpException){
                return "Server error";
            }
            return "";
        }

}
