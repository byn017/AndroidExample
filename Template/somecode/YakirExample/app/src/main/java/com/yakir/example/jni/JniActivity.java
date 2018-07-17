package com.yakir.example.jni;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.yakir.example.R;
import com.yakir.example.parcelable.MyParcelable;

/**
 * Created by Yakir Nahum on 6/26/2018.
 */

public class JniActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jni_test);

        Button testButton = (Button) findViewById(R.id.test_jni);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String test = test();
                Toast.makeText(getBaseContext(),test,Toast.LENGTH_LONG).show();
            }
        });


    }

    /*
    * Loading our lib
    */
    static {
        System.loadLibrary("jni");
    }

    static native String test();
}
