package com.vit.customerapp.data.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vit.customerapp.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient(String baseUrl) {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

        OkHttpClient defaultHttpClient =
                new OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS)
                        .addInterceptor(chain -> {
                            Request request = chain.request()
                                    .newBuilder()
                                    .addHeader("Content-Type", "application/json")
                                    .build();

                            return chain.proceed(request);
                        })
                        .addInterceptor(logging)
                        .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(defaultHttpClient)
                .build();
        return retrofit;
    }
}
