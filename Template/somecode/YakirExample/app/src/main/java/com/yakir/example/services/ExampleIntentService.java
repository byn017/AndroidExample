package com.yakir.example.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.yakir.example.ExampleActivity;

/**
 * Created by Yakir Nahum on 6/27/2018.
 */

public class ExampleIntentService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     */
    public ExampleIntentService() {
        super("Example IntentService");

    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(ExampleActivity.TAG, "ExampleIntentService onHandleIntent: ");

    }
}
