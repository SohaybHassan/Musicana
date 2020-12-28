package com.prography.musicana.network;


import com.prography.musicana.feature.login.model.DataLogin;
import com.prography.musicana.feature.onboard.model.DataPoalycey;
import com.prography.musicana.feature.onboard.model.OnpordingModel;
import com.prography.musicana.feature.onboard.model.termcondtion.GenerlClass;
import com.prography.musicana.feature.regester.model.RegesterModel;
import com.prography.musicana.feature.regester.model.gender.RequesBody;


import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiPartLink {
    //OnBording
    @GET("data/onboarding")
    Call<OnpordingModel> getOnpordingData();

    //privacypolicy
    @GET("data/privacypolicy")
    Call<DataPoalycey> getprivacypolicy();

    //TermAndCondtion
    @GET("data/TermsAndConditions")
    Call<GenerlClass> getTermsAndConditions();

    //regester

    @POST("user/register")
    Call<RegesterModel> newUser(@Body RequestBody requestBody);

    /*@Body String firdName, @Body String lastName, @Body String phone, @Body String email
            , @Body String password, @Body String country, @Body String gender*/

    @GET("data/genders")
    Call<RequesBody> getGender();


    @GET("data/countries")
    Call<com.prography.musicana.feature.regester.model.country.RequesBody> getCuntry();

    //logdin
    @POST("user/login")
    Call<DataLogin> login(@Body RequestBody requestBody);


}
