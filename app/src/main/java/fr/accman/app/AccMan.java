package fr.accman.app;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import java.util.zip.Inflater;

/**
 * Created by nanosvir on 24 Nov 15.
 */
public class AccMan extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        AccMan.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return AccMan.context;
    }

    public static String getStringResource(int resourceId) {
        return AccMan.getAppContext().getString(resourceId);
    }

    public static LayoutInflater getInflater(){
        return (LayoutInflater) AccMan.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public static View inflate(int res) {
        return getInflater().inflate(res, null);
    }
}
