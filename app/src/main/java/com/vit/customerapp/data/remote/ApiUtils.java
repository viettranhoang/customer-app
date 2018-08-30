package com.vit.customerapp.data.remote;

public class ApiUtils {

    private ApiUtils() {}

    public static final String BASE_URL = "http://10.10.0.7:8082/";

    public static APIService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
