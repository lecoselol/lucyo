package de.culo.lucyo.lights;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import java.io.IOException;
import java.net.UnknownHostException;

public class LucyoLightController extends IntentService {

    private static final String CONTROLLER_IP = "192.168.0.142";

    private static final String ACTION_TURN_OFF = "de.culo.lucyo.lights.actions.TURN_OFF";
    private static final String ACTION_TURN_ON = "de.culo.lucyo.lights.actions.TURN_ON";

    public LucyoLightController() {
        super("LightController");
    }

    @NonNull
    private WiFiBox createBox() throws UnknownHostException {
        return new WiFiBox(CONTROLLER_IP);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            switch (intent.getAction()) {
                case ACTION_TURN_OFF:
                    createBox().off();
                    break;
                case ACTION_TURN_ON:
                    createBox().on();
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void turnOn(Context c) {
        startWithAction(c, ACTION_TURN_ON);
    }

    public static void turnOff(Context c) {
        startWithAction(c, ACTION_TURN_OFF);
    }

    private static void startWithAction(Context c, String action) {
        Intent intent = new Intent(action, null, c, LucyoLightController.class);
        c.startService(intent);
    }
}
