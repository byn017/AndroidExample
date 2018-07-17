package com.yakir.example;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.yakir.example.list.ListViewActivity;
import com.yakir.example.services.ExampleIntentService;


public class ExampleActivity extends AppCompatActivity {

    public static final String TAG = "ExampleActivity";
    public static final int EVENT_START_LIST_VIEW_EXAMPLE = 0;
    public static final int EVENT_SEND_EXAMPLE_RECEIVED = 1;

    private static final String ACTION_EXAMPLE_RECEIVED = "com.yakir.example.ACTION_EXAMPLE_RECEIVED";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);

        final RecyclerView rv = (RecyclerView) findViewById(R.id.recyclerView);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new ExampleAdapter(mHandler));

        IntentFilter filter = new IntentFilter();
        filter = new IntentFilter();
        filter.addAction(ACTION_EXAMPLE_RECEIVED);
        registerReceiver(mIntentReceiver, filter);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mIntentReceiver != null)
            unregisterReceiver(mIntentReceiver);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.about:
                AlertDialog.Builder builder = new AlertDialog.Builder(ExampleActivity.this);

                builder.setTitle("About");
                builder.setMessage("This is Android base examples");

                builder.setPositiveButton("EXIT", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: ");
    }

    /**
     * Handler to read and update MCFG info
     */

    public Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case EVENT_START_LIST_VIEW_EXAMPLE:
                    Intent listViewActivity = new Intent(ExampleActivity.this, ListViewActivity.class);
                    startActivityForResult(listViewActivity,1);
                    break;
                case EVENT_SEND_EXAMPLE_RECEIVED:
                    Intent intent = new Intent("com.yakir.example.ACTION_EXAMPLE_RECEIVED");
                    intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);

                    sendBroadcast(intent);
                    Log.d(ExampleActivity.TAG, "sendBroadcast ");

                default:
                    break;
            }
        }
    };

    /**
     * This handles the response from Modem Service.
     */
    BroadcastReceiver mIntentReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.d(ExampleActivity.TAG,"ExampleReceiver Received: " + action);

            if (action.equals(ACTION_EXAMPLE_RECEIVED)) {
                Intent mIntent = new Intent(context, ExampleIntentService.class);
                context.startService(mIntent);
            } else {
                Log.w(TAG, " received unexpected Intent: " + intent.getAction());
            }
        }
    };


}
