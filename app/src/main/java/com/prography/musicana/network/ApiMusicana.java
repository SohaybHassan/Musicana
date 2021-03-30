package com.prography.musicana.network;


import com.prography.musicana.feature.bottomNavigationViewFragment.profile.model.allsettings.SettingsResponse;
import com.prography.musicana.feature.bottomNavigationViewFragment.profile.model.logout.Logout;
import com.prography.musicana.feature.bottomNavigationViewFragment.profile.model.profiledata.ProfileData;
import com.prography.musicana.feature.bottomNavigationViewFragment.profile.model.updataprofile.UpdateProfileResponse;
import com.prography.musicana.feature.login.model.Login;
import com.prography.musicana.feature.onboard.model.onPording.OnpordingModel;
import com.prography.musicana.feature.onboard.model.privacypolicy.DataPoalycey;
import com.prography.musicana.feature.onboard.model.termcondtion.TermsAndConditions;
import com.prography.musicana.feature.regester.model.RegesterModel;
import com.prography.musicana.feature.regester.model.country.Countries;
import com.prography.musicana.feature.regester.model.gender.Genders;
import com.prography.musicana.feature.regester.model.resendverification.ResendVerificationCode;
import com.prography.musicana.feature.regester.model.verification.VerificatioEmail;
import com.prography.musicana.feature.status.changestatus.ChangeStatus;
import com.prography.musicana.feature.status.closestatus.CloseStatus;
import com.prography.musicana.feature.status.newstatus.NewStatus;


import java.util.List;

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
    Call<OnpordingModel> getOnpordingData();

    //privacypolicy
    @GET("data/privacypolicy")
    Call<DataPoalycey> getprivacypolicy();

    //TermAndCondtion
    @GET("data/TermsAndConditions")
    Call<TermsAndConditions> getTermsAndConditions();

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
    Call<Genders> getGender();


    @GET("data/countries")
    Call<Countries> getCuntry();

    //logdin
    @FormUrlEncoded
    @POST("user/login")
    Call<Login> login(@Field("email") String email,
                      @Field("password") String pass,
                      @Field("device") String device,
                      @Field("UUID") String uuid,
                      @Field("device_name") String deviceName);

    //verification
    @FormUrlEncoded
    @POST("user/verification")
    Call<VerificatioEmail> verificationCode(@Field("verify_code") String verify_code,
                                            @Field("password") String password,
                                            @Field("email") String email,
                                            @Field("device") String device,
                                            @Field("UUID") String uuid,
                                            @Field("device_name") String devicename);

    //resend
    @FormUrlEncoded
    @POST("user/verification/resend")
    Call<ResendVerificationCode> resendVerificationCode(@Field("email") String Email);


    //logout
    @GET("user/logout")
    Call<Logout> logout();

    //user profile
    @GET("user/profile")
    Call<ProfileData> getProfileData();




    //TODO start here


    @Multipart
    @POST("user/profile/updatedata")
    Call<UpdateProfileResponse> updateProfile(@Part("firstname") RequestBody first_name,
                                              @Part("middlename") RequestBody middle_name,
                                              @Part("lastname") RequestBody last_name,
                                              @Part("phone") RequestBody phone,
                                              @Part("gender") RequestBody gender,
                                              @Part("country") RequestBody country,
                                              @Part MultipartBody.Part image);
    // user settings
    @GET("user/settings")
    Call<SettingsResponse> getAllSettings();

    @FormUrlEncoded
    @POST("user/settings/change")
    Call<SettingsResponse> changeSettings(@Field("mood") String mood,
                                          @Field("language") String language,
                                          @Field("additional_screen") String additional_screen,
                                          @Field("auto_update") String auto_update,
                                          @Field("background") String background,
                                          @Field("audio") String audio,
                                          @Field("location") String location);



    @POST("user/profile/updatedata/email")
    Call<UpdateProfileResponse> updateEmail(@Field("email") String email);


    @POST("user/profile/updatedata/password")
    Call<UpdateProfileResponse> updatePassword(@Field("password") String password);


    //userStatus
    @POST("user/status")
    Call<CloseStatus> colseStatus();


    @POST("user/status/change")
    Call<ChangeStatus> changeStatus(@Field("status_to") String Background);


    @POST("user/status/new")
    Call<NewStatus> newStatus(@Field("UUID") String uuid);





//
//    @POST("user/settings/change")
//    Call<> changeSettings(@Field("mood") String mood,
//                          @Field("language") String language,
//                          @Field("additional_screen") String additional_screen,
//                          @Field("auto_update") String auto_update,
//                          @Field("background") String background,
//                          @Field("audio") String audio,
//                          @Field("location") String location);


    //music

//    @POST("music/search/")
//    Call<> search(@Field("q") String text, @Field("nextPage") String page);
//
//    @POST("music/home")
//    Call<> homeData(@Field("vpage") String vpage, @Field("cpage") String cpage);


    //view
//    @POST("music/view/channel")
//    Call<> viewchannel(@Field("channelId") String id);
//
//
//    @POST("music/view/video")
//    Call<> viewvideo(@Field("videoId") String id);


    //Favorite

//    @POST("music/Favorite/add/")
//    Call<> addToFavorite(@Field("videoId") String id);
//
//    @POST("music/Favorite/add/playlist")
//    Call<> addPlaylistToFavorite(@Field("playlistId") String id);
//
//
//    @GET("music/Favorite/")
//    Call<> getAllFavorit();


    //playList
//    @GET("music/playlist/")
//    Call<> getAllPlayList();
//
//    @POST("music/playlist/create")
//    Call<> createPlaylist(@Field("playlistName") String name);
//
//    @POST("music/playlist/add")
//    Call<> addToPlayList(@Field("songId") String songid, @Field("playlistId") String playListid);
//
//    @POST("music/playlist/view")
//    Call<> ViewPliatListSong(@Field("playlistId") String playlistid);
//
//    @POST("music/playlist/delete")
//    Call<> deletePlayList(@Field("songId") String songid, @Field("playlistId") String listid);


}
