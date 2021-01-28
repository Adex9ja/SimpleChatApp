package com.chat.app.cores;

import com.chat.app.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private static RetrofitClientInstance mInstance;
    public static final String BASE_URL = "https://dummyapi.io/data/api/";
    private Retrofit mRetrofit;

    private RetrofitClientInstance() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        Interceptor authInterceptor = chain -> {
            Request newRequest = chain.request().newBuilder()
                    .addHeader("app-id", BuildConfig.API_KEY)
                    .build();
            return chain.proceed(newRequest);
        };

        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        okHttpBuilder.connectTimeout(5, TimeUnit.MINUTES);
        okHttpBuilder.addInterceptor(interceptor);
        okHttpBuilder.addInterceptor(authInterceptor);

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static RetrofitClientInstance getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitClientInstance();
        }
        return mInstance;
    }

    public GetDataService getDataService() {
        return mRetrofit.create(GetDataService.class);
    }
}
