package com.tin.chigua;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by hasee on 7/18/2017.
 */

public class LUtil {

    private static Context mContext = MyApplication.getContext();

    public static void e(String str){
        Log.e("LUtil",str);
    }

    public static void w(String str){
        Log.w("LUtil",str);
    }

    public static void i(String str){
        Log.i("LUtil",str);
    }

    public static void d(String str){
        Log.d("LUtil",str);
    }

    public static void v(String str){
        Log.v("LUtil",str);
    }

    public static void toShrt(String str){
        Toast.makeText(mContext, str, Toast.LENGTH_SHORT);
    }

    public static void toLong(String str){
        Toast.makeText(mContext, str, Toast.LENGTH_LONG);
    }


}
