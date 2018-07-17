package com.yakir.example.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.yakir.example.service.IExampleAidlInterface;


/**
 * Created by Yakir Nahum on 6/25/2018.
 */

public class ExampleAidlService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public final IExampleAidlInterface.Stub binder = new IExampleAidlInterface.Stub() {

        @Override
        public String getMessage(String name) throws RemoteException {
            return "Hello "+ name+", from AIDL";
        }
    };
}
