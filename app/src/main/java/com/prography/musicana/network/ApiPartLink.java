package com.prography.musicana.network;


import com.prography.musicana.feature.bottomNavigationViewFragment.profile.model.Logout;
import com.prography.musicana.feature.login.model.Example;
import com.prography.musicana.feature.onboard.model.DataPoalycey;
import com.prography.musicana.feature.onboard.model.OnpordingModel;
import com.prography.musicana.feature.onboard.model.termcondtion.GenerlClass;
import com.prography.musicana.feature.regester.model.RegesterModel;
import com.prography.musicana.feature.regester.model.gender.RequesBody;
import com.prography.musicana.feature.regester.model.resendverification.ResendVerification;
import com.prography.musicana.feature.regester.model.verification.VerificationRespone;


import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
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
    @FormUrlEncoded
    @POST("user/register")
    Call<RegesterModel> newUser(@Field("firstname") String firstname,
                                @Field("lastname") String lastname,
                                @Field("phone") String phone,
                                @Field("email") String email,
                                @Field("password") String password,
                                @Field("country") String country,
                                @Field("gender") String gender);

    @GET("data/genders")
    Call<RequesBody> getGender();


    @GET("data/countries")
    Call<com.prography.musicana.feature.regester.model.country.RequesBody> getCuntry();

    //logdin
    @FormUrlEncoded
    @POST("user/login")
    Call<Example> login(@Field("email") String email,
                        @Field("password") String pass,
                        @Field("device") String device,
                        @Field("UUID") String uuid,
                        @Field("device_name") String deviceName);

    //verification
    @FormUrlEncoded
    @POST("user/verification")
    Call<VerificationRespone> verificationCode(@Field("verify_code") String verify_code,
                                               @Field("password") String password,
                                               @Field("email") String email,
                                               @Field("device") String device,
                                               @Field("UUID") String uuid,
                                               @Field("device_name") String devicename);

    @FormUrlEncoded
    @POST("user/verification/resend")
    Call<ResendVerification> resendVerificationCode(@Field("email") String Email);



    @GET("user/logout")
    Call<Logout> logout();



}
