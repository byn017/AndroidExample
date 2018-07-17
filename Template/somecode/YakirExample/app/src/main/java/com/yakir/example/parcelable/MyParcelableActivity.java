package com.yakir.example.parcelable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


import com.yakir.example.R;

/**
 * Created by Yakir Nahum on 6/26/2018.
 */

public class MyParcelableActivity  extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parcelable_activity);

        Intent mIntent = getIntent();
        MyParcelable myParcelable = mIntent.getParcelableExtra("MyParcelableKey");

        TextView name = (TextView) findViewById(R.id.name);
        name.setText(myParcelable.getName());
        TextView age = (TextView) findViewById(R.id.age);
        age.setText(Integer.toString(myParcelable.getAge()));
        TextView description = (TextView) findViewById(R.id.description);
        description.setText(myParcelable.getDescription().toString());
    }
}
