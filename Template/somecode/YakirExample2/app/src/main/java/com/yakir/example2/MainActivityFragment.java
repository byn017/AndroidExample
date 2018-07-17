package com.yakir.example2;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {


    private static final int CHANGE_TIME = 0;
    TextView time;
    IMyAidlInterface myAIDL = null;
    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        Button b= (Button) rootView.findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "cdcdcdcd",Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getContext(),MainService.class);
                intent.putExtra("TEST", true);
                getContext().startService(intent);


            }
        });

        Button b1= (Button) rootView.findViewById(R.id.button1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (myAIDL != null) {
                    try {
                        myAIDL.setTime(10);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }

                new LongOperation(true).execute("yakir","nahum");


            }
        });


        time = (TextView) rootView.findViewById(R.id.sample_text);

        IntentFilter filter = new IntentFilter();
        filter.addAction("UPDATE_UI_TIME");
        getContext().registerReceiver(myReceiver,filter);
        return rootView;
    }

    @Override
    public void onStart() {

        if (myAIDL == null) {
            Intent intent = new Intent(getContext(),MainService.class);
            getContext().bindService(intent,connection,Context.BIND_AUTO_CREATE);
        }
        super.onStart();
    }

private ServiceConnection connection = new ServiceConnection() {
    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        myAIDL = IMyAidlInterface.Stub.asInterface(iBinder);
        Toast.makeText(getContext(),	"Service Connected", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        myAIDL = null;
        Toast.makeText(getContext(),	"Service DisConnected", Toast.LENGTH_SHORT).show();
    }
};

    BroadcastReceiver myReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction().equals("UPDATE_UI_TIME")) {
                if (intent.hasExtra("TIME")) {
                    int newTime = intent.getIntExtra("TIME",-1);
                    if (newTime != -1) {
                        Message msg = new Message();
                        msg.what = CHANGE_TIME;
                        msg.arg1 = newTime;
                        myUiHandler.sendMessage(msg);
                    }
                }
            }

        }
    };

    Handler myUiHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case CHANGE_TIME:
                    if (msg.arg1 >= -1) {
                        time.setText(Integer.toString(msg.arg1));
                    }

                    break;
            }
        }
    };


    private class LongOperation extends AsyncTask<String, Void, String> {
        public LongOperation(boolean showLoading) {
            super();
            Log.d("YNH", "LongOperation: " + showLoading);
        }
        @Override
        protected String doInBackground(String... params) {

            Log.d("YNH", "doInBackground: " + params[0]);
            Log.d("YNH", "doInBackground: " + params[1]);

            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getContext(),result,Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {}
    }

}
