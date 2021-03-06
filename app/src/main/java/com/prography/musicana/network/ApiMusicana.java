package com.prography.musicana.network;


import com.prography.musicana.model.DataModel;


import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiMusicana {
    //OnBording
    @GET("data/onboarding")
    Call<DataModel> getOnBoardingData();

    //PrivacyPolicy
    @GET("data/privacypolicy")
    Call<DataModel> getPrivacyPolicy();

    //TermAndCondition
    @GET("data/TermsAndConditions")
    Call<DataModel> getTermsAndConditions();

    //regester
    @FormUrlEncoded
    @POST("user/register")
    Call<DataModel> newUser(@Field("firstname") String firstname,
                            @Field("lastname") String lastname,
                            @Field("phone") String phone,
                            @Field("email") String email,
                            @Field("password") String password,
                            @Field("country") String country,
                            @Field("gender") String gender);

    @GET("data/genders")
    Call<DataModel> getGender();


    @GET("data/countries")
    Call<DataModel> getCountry();

    //login
    @FormUrlEncoded
    @POST("user/login")
    Call<DataModel> login(@Field("email") String email,
                          @Field("password") String pass,
                          @Field("device") String device,
                          @Field("UUID") String uuid,
                          @Field("device_name") String deviceName);

    //verification
    @FormUrlEncoded
    @POST("user/verification")
    Call<DataModel> verificationCode(@Field("verify_code") String verify_code,
                                     @Field("password") String password,
                                     @Field("email") String email,
                                     @Field("device") String device,
                                     @Field("UUID") String uuid,
                                     @Field("device_name") String devicename);

    //resend
    @FormUrlEncoded
    @POST("user/verification/resend")
    Call<DataModel> resendVerificationCode(@Field("email") String Email);


    //logout
    @GET("user/logout")
    Call<DataModel> logout();

    //user profile
    @GET("user/profile")
    Call<DataModel> getProfileData();


    //TODO start here


    @Multipart
    @POST("user/profile/updatedata")
    Call<DataModel> updateProfile(@Part("firstname") RequestBody first_name,
                                              @Part("middlename") RequestBody middle_name,
                                              @Part("lastname") RequestBody last_name,
                                              @Part("phone") RequestBody phone,
                                              @Part("gender") RequestBody gender,
                                              @Part("country") RequestBody country,
                                              @Part MultipartBody.Part image);

    // user settings
    @GET("user/settings")
    Call<DataModel> getAllSettings();

    @FormUrlEncoded
    @POST("user/settings/change")
    Call<DataModel> changeSettings(@Field("mood") String mood,
                                   @Field("language") String language,
                                   @Field("additional_screen") String additional_screen,
                                   @Field("auto_update") String auto_update,
                                   @Field("background") String background,
                                   @Field("audio") String audio,
                                   @Field("location") String location);


    @POST("user/profile/updatedata/email")
    Call<DataModel> updateEmail(@Field("email") String email);


    @POST("user/profile/updatedata/password")
    Call<DataModel> updatePassword(@Field("password") String password);


    //userStatus
    @FormUrlEncoded
    @POST("user/status")
    Call<DataModel> colseStatus();

    @FormUrlEncoded
    @POST("user/status/change")
    Call<DataModel> changeStatus(@Field("status_to") String Background);

    @FormUrlEncoded
    @POST("user/status/new")
    Call<DataModel> newStatus(@Field("UUID") String uuid);

    @FormUrlEncoded
    @POST("music/Favorite/add/")
    Call<DataModel> addToFavorite(String songid);

    @FormUrlEncoded
    @POST("music/playlist/add")
    Call<DataModel> addToPlayList(String songID, String playlistID);

    @FormUrlEncoded
    @POST("music/playlist/view")
    Call<DataModel> ViewPliatListSong(String playlistid);

    @FormUrlEncoded
    @POST("music/playlist/delete")
    Call<DataModel> deleteSong(String songid, String listid);

    @GET("music/playlist/")
    Call<DataModel> getAllPlayList();

    @FormUrlEncoded
    @POST("music/playlist/create")
    Call<DataModel> createPlaylist(String plsylistName);

    @FormUrlEncoded
    @POST("music/home")
    Call<DataModel> homeData(String vPage, String cPage);

    @FormUrlEncoded
    @POST("music/search/")
    Call<DataModel> search(String q, String nextPage);


//
//    @POST("user/settings/change")
//    Call<> changeSettings(@Field("mood") String mood,
//                          @Field("language") String language,
//                          @Field("additional_screen") String additional_screen,
//                          @Field("auto_update") String auto_update,
//                          @Field("background") String background,
//                          @Field("audio") String audio,
//                          @Field("location") String location);


    //view
//    @POST("music/view/channel")
//    Call<> viewchannel(@Field("channelId") String id);
//
//
//    @POST("music/view/video")
//    Call<> viewvideo(@Field("videoId") String id);


    //Favorite

//
//    @POST("music/Favorite/add/playlist")
//    Call<> addPlaylistToFavorite(@Field("playlistId") String id);
//
//
//    @GET("music/Favorite/")
//    Call<> getAllFavorit();


}
