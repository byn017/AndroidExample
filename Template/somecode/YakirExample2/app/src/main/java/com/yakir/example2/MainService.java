package com.yakir.example2;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by WKPV68 on 7/16/2018.
 */

public class MainService extends Service {


    public static final int EVENT_SEND_EXAMPLE_RECEIVED = 0;
    public static final int EVENT_UPDATE_TIMER = 1;
    public static final int EVENT_UPDATE_TIMER1 = 10;

    private Timer timer;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

   public final IMyAidlInterface.Stub binder = new IMyAidlInterface.Stub() {
       @Override
       public void setTime(int  time) throws RemoteException {
           mHandler.sendEmptyMessage(EVENT_UPDATE_TIMER1);
       }
   };


    @Override
    public void onCreate() {
        Log.d("MainService", "onDestroy: ");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d("MainService", "onStartCommand: ");
        if (intent.hasExtra("TEST")) {
            boolean testBoolrean = intent.getBooleanExtra("TEST",false);
            Log.d("MainService", "testBoolrean: " + testBoolrean);
            mHandler.sendEmptyMessage(EVENT_UPDATE_TIMER);

        }
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.d("MainService", "onDestroy: ");
        super.onDestroy();
    }


    public Handler mHandler = new Handler() {
        Runnable runnable;
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {

                case EVENT_SEND_EXAMPLE_RECEIVED:
                    Toast.makeText(getApplicationContext(),"EVENT_SEND_EXAMPLE_RECEIVED",Toast.LENGTH_LONG).show();
                    Log.d("MainService", "show toast ");
                case  EVENT_UPDATE_TIMER:
                    Intent myIntent = new Intent("UPDATE_UI_TIME");
                    myIntent.putExtra("TIME",1);
                    getApplicationContext().sendBroadcast(myIntent);
                    if (timer != null) {
                        timer.cancel();
                    }

                    mHandler.removeCallbacks(runnable);
                    mHandler.postDelayed(runnable =  new Runnable() {
                        int time = 111;
                        @Override
                        public void run() {
                            time++;
                            Intent myIntent = new Intent("UPDATE_UI_TIME");
                            myIntent.putExtra("TIME",time);
                            getApplicationContext().sendBroadcast(myIntent);
                            mHandler.postDelayed(runnable, 1000);
                        }
                    },1000);
                    break;
                case  EVENT_UPDATE_TIMER1:
                    mHandler.removeCallbacks(runnable);
                    Intent myIntent1 = new Intent("UPDATE_UI_TIME");
                    myIntent1.putExtra("TIME",10);
                    getApplicationContext().sendBroadcast(myIntent1);
                    timer = new Timer();
                    timer.scheduleAtFixedRate(new TimerTask() {
                        int time = 1;
                        @Override
                        public void run() {
                            time++;
                            Intent myIntent1 = new Intent("UPDATE_UI_TIME");
                            myIntent1.putExtra("TIME",time);
                            getApplicationContext().sendBroadcast(myIntent1);
                        }
                    }, 0, 1000);//put here time 1000 milliseconds=1 second

                    break;
                default:
                    break;
            }
        }
    };




}

