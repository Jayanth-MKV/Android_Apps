package com.vugido.foods.vugidodeliveryagent.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * BroadcastReceiver to wait for SMS messages. This can be registered either
 * in the AndroidManifest or at runtime.  Should filter Intents on
 * SmsRetriever.SMS_RETRIEVED_ACTION.
 */
public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {


                    Intent hhtpIntent = new Intent(context, RingToneService.class);
                    context.startService(hhtpIntent);
                    // Extract one-time code from the message and complete verification
                    // by sending the code back to your server.


    }




}

