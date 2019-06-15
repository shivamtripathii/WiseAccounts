package com.wiselap.accounts.utils;

public class URLS {

    public static String ENVIRONMENT = "Testing";

    public static final String SCHEME = "http";

    public static final String AUTHORITY = "www.wiselap.in:8081";

    //public static final String BASE_PATH = "wiselap-0.0.1-Testing";//wiselap-0.0.1-Developer
    public static final String BASE_PATH = "wisebooks";

    public static final String BASE_URL = SCHEME+"://"+AUTHORITY+"/"+BASE_PATH+"/";


    public static final String getExpenseTypes = "getExpenseTypes";
    public static final String getEntityTypes = "getEntityTypes";
    public static final String addEntityTypes = "addEntityTypes";
    public static final String getConfigurationExpenseTypes = "getConfigurationExpenseTypes";
    public static final String addConfigurationExpenseTypes = "addConfigurationExpenseTypes";
    public static final String updateConfigurationExpenseTypes = "updateConfigurationExpenseTypes";
    public static final String deleteExpenseTypes = "deleteExpenseTypes";
    public static final String addAccountingProfile = "addAccountingProfile";
}
