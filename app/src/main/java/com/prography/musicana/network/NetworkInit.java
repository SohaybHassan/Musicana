package com.prography.musicana.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkRequest;

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
    private static NetworkInit instance;
    private static boolean myConnection;

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

    public static NetworkInit getInstance(Boolean isLogin) {
        if (instance == null) {
            instance = new NetworkInit(isLogin);
        }
        return instance;
    }

    public static void isNetworkConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkRequest.Builder builder = new NetworkRequest.Builder();
        connectivityManager.registerNetworkCallback(
                builder.build(),
                new ConnectivityManager.NetworkCallback() {
                    @Override
                    public void onAvailable(Network network) {
                        myConnection = true;
                    }

                    @Override
                    public void onLost(Network network) {
                        myConnection = false;
                    }
                }

        );

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
                .readTimeout(7000, TimeUnit.SECONDS)
                .connectTimeout(7000, TimeUnit.SECONDS)
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)  //Show Data body
                .build();
        apiPartLink = retrofit.create(ApiPartLink.class);


    }
}
