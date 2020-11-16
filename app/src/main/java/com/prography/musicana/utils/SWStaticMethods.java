package com.prography.musicana.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public  class SWStaticMethods {


     public static void  intentWithoutData(Activity firstActivity,Class<?> call){
           Intent intentWithoutData= new Intent(firstActivity,call);
         firstActivity.startActivity(intentWithoutData);
     }

    public static void  intentWithData(Activity firstActivity, Class<?> call, Bundle bundle){
        Intent intentWithData= new Intent(firstActivity,call);

        intentWithData.putExtra("data",bundle);
        firstActivity.startActivity(intentWithData);
    }




}
