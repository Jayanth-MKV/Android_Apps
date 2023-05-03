package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Status;
import com.foodhub.vugido.app_config_variables.ConfigVariables;
import com.foodhub.vugido.services.HttpService;

/**
 * BroadcastReceiver to wait for SMS messages. This can be registered either
 * in the AndroidManifest or at runtime.  Should filter Intents on
 * SmsRetriever.SMS_RETRIEVED_ACTION.
 */
public class MySMSBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (SmsRetriever.SMS_RETRIEVED_ACTION.equals(intent.getAction())) {
            Bundle extras = intent.getExtras();
            assert extras != null;
            Status status = (Status) extras.get(SmsRetriever.EXTRA_STATUS);

            assert status != null;
            switch(status.getStatusCode()) {
                case CommonStatusCodes.SUCCESS:
                    // Get SMS message contents
                    String message = (String) extras.get(SmsRetriever.EXTRA_SMS_MESSAGE);

                    assert message != null;
                    Log.e("message otp",message);
                    Log.e("OTP",getVerificationCode(message));



                    Intent hhtpIntent = new Intent(context, HttpService.class);
                    hhtpIntent.putExtra("otp", getVerificationCode(message));
                    context.startService(hhtpIntent);
                    // Extract one-time code from the message and complete verification
                    // by sending the code back to your server.
                    break;
                case CommonStatusCodes.TIMEOUT:
                    // Waiting for SMS timed out (5 minutes)
                    // Handle the error ...
                    Log.e("Connection","Time Out");
                    break;
            }
        }
    }

    private String getVerificationCode(String message) {
        String code = null;
        int index = message.indexOf(ConfigVariables.OTP_DELIMITER);

        if (index != -1) {
            int start = index + 2;
            int length = 6;
            code = message.substring(start, start + length);
            return code;
        }

        return code;
    }



}

