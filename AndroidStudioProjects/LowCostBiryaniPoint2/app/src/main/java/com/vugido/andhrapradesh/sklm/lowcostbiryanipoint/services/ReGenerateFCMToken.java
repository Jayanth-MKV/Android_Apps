package com.vugido.andhrapradesh.sklm.lowcostbiryanipoint.services;

import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.Nullable;

import com.google.firebase.iid.FirebaseInstanceId;

import java.io.IOException;

public class ReGenerateFCMToken extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */

    private static String TAG = ReGenerateFCMToken.class.getSimpleName();

    public ReGenerateFCMToken() {
        super(TAG);
    }





    @Override
    protected void onHandleIntent(@Nullable Intent intent) {


        try {
            FirebaseInstanceId.getInstance().setFcmAutoInitEnabled(true);
            FirebaseInstanceId.getInstance().deleteInstanceId();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
