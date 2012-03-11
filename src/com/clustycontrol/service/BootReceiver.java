
package com.clustycontrol.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(final Context context, final Intent intent) {
        Intent i = new Intent(context, BootService.class);
        context.startService(i);
    }
}
