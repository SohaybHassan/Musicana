package com.prography.musicana.network;


import com.prography.musicana.feature.bottomNavigationViewFragment.profile.model.logout.Logout;
import com.prography.musicana.feature.bottomNavigationViewFragment.profile.model.allsettings.AllSettings;
import com.prography.musicana.feature.bottomNavigationViewFragment.profile.model.profiledata.ProfileData;
import com.prography.musicana.feature.bottomNavigationViewFragment.profile.model.status.CloseStatus;
import com.prography.musicana.feature.bottomNavigationViewFragment.profile.model.updataprofile.Updatedata;
import com.prography.musicana.feature.login.model.Login;
import com.prography.musicana.feature.onboard.model.onPording.OnpordingModel;
import com.prography.musicana.feature.onboard.model.privacypolicy.DataPoalycey;
import com.prography.musicana.feature.onboard.model.termcondtion.TermsAndConditions;
import com.prography.musicana.feature.regester.model.RegesterModel;
import com.prography.musicana.feature.regester.model.country.Countries;
import com.prography.musicana.feature.regester.model.gender.Genders;
import com.prography.musicana.feature.regester.model.resendverification.ResendVerificationCode;
import com.prography.musicana.feature.regester.model.verification.VerificatioEmail;


import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
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


    @FormUrlEncoded
    @Multipart
    @POST("user/profile/updatedata")
    Call<Updatedata> updatedata(@Field("firstname") String verify_code,
                                @Field("middlename") String password,
                                @Field("lastname") String email,
                                @Field("phone") String device,
                                @Field("gender") String uuid,
                                @Field("country") String devicename,
                                @Field("imag") MultipartBody.Part image);


    @POST("user/profile/updatedata/emai")
    Call<Updatedata> updatedataemai(@Field("email") String email);


    @POST("user/profile/updatedata/password")
    Call<Updatedata> updatedatapassword(@Field("email") String email);


    //userStatus
    @POST("user/status")
    Call<CloseStatus> colseStatus();


//    @POST("user/status/change")
//    Call<> changeStatus(@Field("status_to") String Background);
//
//
//    @POST("user/status/new")
//    Call<> newStatus(@Field("UUID") String uuid);

    // user settings
    @GET("user/settings")
    Call<AllSettings> getAllSettings();
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
