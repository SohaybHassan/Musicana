package com.prography.musicana.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.prography.musicana.BuildConfig;
import com.prography.musicana.R;
import com.prography.musicana.viewmodel.PlaylsitViewModel;

public class SWStaticMethods {


    private PlaylsitViewModel playlsitViewModel;
    private ViewModelStoreOwner context;
    LifecycleOwner lifecycleOwner;

    public SWStaticMethods(ViewModelStoreOwner context, LifecycleOwner lifecycleOwner) {
        this.context = context;
        this.lifecycleOwner = lifecycleOwner;
        playlsitViewModel = new ViewModelProvider(context).get(PlaylsitViewModel.class);
    }

    public static void intentWithoutData(Activity firstActivity, Class<?> call) {
        Intent intentWithoutData = new Intent(firstActivity, call);
        firstActivity.startActivity(intentWithoutData);
        firstActivity.finish();
    }

    public static void intentWithData(Activity firstActivity, Class<?> call, Bundle bundle) {
        Intent intentWithData = new Intent(firstActivity, call);
        intentWithData.putExtra("data", bundle);
        firstActivity.startActivity(intentWithData);
        firstActivity.finish();
    }

    public static void intentWithDataWithoutfinish(Activity firstActivity, Class<?> call, Bundle bundle) {
        Intent intentWithData = new Intent(firstActivity, call);
        intentWithData.putExtra("data", bundle);
        firstActivity.startActivity(intentWithData);

    }

    public static void intentWithOutDataAndFinish(Activity firstActivity, Class<?> call) {
        Intent intentWithoutData = new Intent(firstActivity, call);
        firstActivity.startActivity(intentWithoutData);

    }

    public static void shareApp(Activity activity) {
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
            String shareMessage = "\nLet me recommend you this application\n\n";
            shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            activity.startActivity(Intent.createChooser(shareIntent, "choose one"));
        } catch (Exception e) {
            //e.toString();
        }
    }

//    public static Object<R> GTO(<R> type){
//
//
//    }

}

