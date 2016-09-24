package de.culo.lucyo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import de.culo.lucyo.log.Poet;

public class KickoffReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Poet.screams("BOOT RECEIVED. DO STUFF.");
    }

}
