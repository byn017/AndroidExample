package com.yakir.example.list;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.yakir.example.ExampleActivity;
import com.yakir.example.R;
import com.yakir.example.fragment.Fragment2Activity;
import com.yakir.example.fragment.FragmentActivity;
import com.yakir.example.notification.NotificationAndDialogActivity;
import com.yakir.example.services.ExampleAidlActivity;

/**
 * Created by Yakir Nahum on 6/25/2018.
 */

public class ListViewActivity extends Activity {
    // Array of strings...
    ListView exampleListView;
    String exampleList[] = {"Item 1","Item 2", "Item 3", "Item 4","Item 5","Item 6"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_example);
        exampleListView = (ListView)findViewById(R.id.exampleListView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.listview_example, R.id.textView, exampleList);
        exampleListView.setAdapter(arrayAdapter);

        exampleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),exampleList[position],Toast.LENGTH_LONG).show();
            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(ExampleActivity.TAG, "ListViewActivity onPause: ");
        setResult(Activity.RESULT_OK);
        finish();
    }


}
