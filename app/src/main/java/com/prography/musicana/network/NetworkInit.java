package com.prography.musicana.network;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.prography.musicana.network.ApiConstant.ACCEPT_LANGUAGE;
import static com.prography.musicana.network.ApiConstant.HEADER_ACCEPT_LANGUAGE;
import static com.prography.musicana.network.ApiConstant.PASEURL;
import static com.prography.musicana.network.ApiConstant.TOKEN;

public class NetworkInit {

    private ApiPartLink apiPartLink;


    public NetworkInit(Boolean isLogin) {

        Interceptor interceptor;
        if (isLogin) {
            interceptor = chain -> {
                Request.Builder builder = chain.request().newBuilder()
                        .addHeader(HEADER_ACCEPT_LANGUAGE, ACCEPT_LANGUAGE)
                        .addHeader(TOKEN, "");
                return chain.proceed(builder.build());
            };
        } else {
            interceptor = chain -> {
                Request.Builder builder = chain.request().newBuilder()
                        .addHeader(HEADER_ACCEPT_LANGUAGE, ACCEPT_LANGUAGE);
                return chain.proceed(builder.build());
            };
        }
        init(interceptor);
    }

    public ApiPartLink getRetrofitApis() {
        return apiPartLink;
    }

    public void init(Interceptor interceptorToHeaderData) {
        //Show Data body
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(interceptorToHeaderData)
                .readTimeout(6000, TimeUnit.SECONDS)
                .connectTimeout(6000, TimeUnit.SECONDS)
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)  //Show Data body
                .build();
        apiPartLink = retrofit.create(ApiPartLink.class);


    }
}
