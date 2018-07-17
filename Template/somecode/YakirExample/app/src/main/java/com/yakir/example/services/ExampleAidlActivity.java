package com.yakir.example.services;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yakir.example.R;
import com.yakir.example.service.IExampleAidlInterface;

/**
 * Created by Yakir Nahum on 6/25/2018.
 */

public class ExampleAidlActivity extends Activity {

    private EditText name;
    private TextView result;
    private IExampleAidlInterface aidlService = null ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aidl_example);
    }

    @Override
    protected void onStart() {
        super.onStart();
        name = (EditText)findViewById(R.id.name);
        result = (TextView) findViewById(R.id.result);
        if (aidlService == null) {

            Intent intent = new Intent(this, ExampleAidlService.class);
            bindService(intent, connection, Context.BIND_AUTO_CREATE);

        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }
    public void getFromAidl(View v) {
        switch (v.getId()) {
            case R.id.getFromAIDL: {


                try {
                    String msg = aidlService.getMessage(name.getText().toString());
                    result.setText(msg);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            aidlService = IExampleAidlInterface.Stub.asInterface(service);
            Toast.makeText(getApplicationContext(),	"Service Connected", Toast.LENGTH_SHORT).show();
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            aidlService = null;
            Toast.makeText(getApplicationContext(), "Service Disconnected", Toast.LENGTH_SHORT).show();
        }
    };

}
