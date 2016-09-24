package de.culo.lucyo.log;

import android.util.Log;

public class Poet {

    private static final String TAG = "lucyo";

    public static void says(String someShit) {
        Log.d(TAG, someShit);
    }

    public static void screams(String someShit) {
        Log.e(TAG, "AAAAAAAAAAAAHHHHHH " + someShit);
    }

}
