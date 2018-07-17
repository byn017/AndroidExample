package com.yakir.example;

import android.content.Intent;

import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.util.Pair;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yakir.example.fragment.FragmentActivity;
import com.yakir.example.fragment.Fragment2Activity;
import com.yakir.example.jni.JniActivity;
import com.yakir.example.list.ListViewActivity;
import com.yakir.example.notification.MyAdapter;
import com.yakir.example.notification.NotificationAndDialogActivity;
import com.yakir.example.parcelable.MyParcelable;
import com.yakir.example.parcelable.MyParcelableActivity;
import com.yakir.example.services.ExampleAidlActivity;
import com.yakir.example.sqlite.SQLiteActivity;
import com.yakir.example.webview.WebViewActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Yakir Nahum on 6/25/2018.
 */

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.MyViewHolder> {

   private  Handler mHandler;

    private final List<Pair<String, String>> listExample = Arrays.asList(

            Pair.create("Fragments1", "Fragments Example"),
            Pair.create("Fragments2", "Fragments Example with tabs"),
            Pair.create("Notification And Dialog", "Example for AlertDialog Notification and ProgressDialog"),
            Pair.create("AIDL", "Local AIDL example"),
            Pair.create("ListView", "Simple ListView example. send message with handler and start ListView with startActivityForResult"),
            Pair.create("WebView", "WebView example."),
            Pair.create("SQLite", "SQLite example."),
            Pair.create("Parcelable", "Parcelable example."),
            Pair.create("JNI", "Simple JNI."),
            Pair.create("Services", "Send sendBroadcast and start IntentService. (see logs for receiving and service start")

    );

    public ExampleAdapter(Handler mHandler) {
        this.mHandler = mHandler;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.activity_example_list_cell, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Pair<String, String> pair = listExample.get(position);
        holder.display(pair);
        holder.position(position);
    }

    @Override
    public int getItemCount() {
        return listExample.size();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private final TextView description;

        private Pair<String, String> currentPair;
        private int currentPosition = -1;

        public MyViewHolder(final View itemView) {
            super(itemView);

            name = ((TextView) itemView.findViewById(R.id.name));
            description = ((TextView) itemView.findViewById(R.id.description));

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    switch (currentPosition) {
                        case 0:
                            Intent fragmentActivity = new Intent(itemView.getContext(), FragmentActivity.class);
                            itemView.getContext().startActivity(fragmentActivity);
                            break;
                        case 1:
                            Intent fragment2Activity = new Intent(itemView.getContext(), Fragment2Activity.class);
                            itemView.getContext().startActivity(fragment2Activity);
                            break;
                        case 2:
                            Intent notificationAndDialog = new Intent(itemView.getContext(), NotificationAndDialogActivity.class);
                            itemView.getContext().startActivity(notificationAndDialog);
                            break;
                        case 3:
                            Intent exampleAidlActivity = new Intent(itemView.getContext(), ExampleAidlActivity.class);
                            itemView.getContext().startActivity(exampleAidlActivity);
                            break;
                        case 4:

                            mHandler.sendEmptyMessage(ExampleActivity.EVENT_START_LIST_VIEW_EXAMPLE);

                            break;
                        case 5:
                            Intent webViewActivity = new Intent(itemView.getContext(), WebViewActivity.class);
                            itemView.getContext().startActivity(webViewActivity);
                            break;
                        case 6:
                            Intent sQLiteActivity = new Intent(itemView.getContext(), SQLiteActivity.class);
                            itemView.getContext().startActivity(sQLiteActivity);
                            break;
                        case 7:
                            ArrayList<String> des = new ArrayList<>();
                            des.add("11111");
                            des.add("22222");
                            MyParcelable mObjects = new MyParcelable("yakir",30,des);
                            Intent myParcelableActivity = new Intent(itemView.getContext(), MyParcelableActivity.class);
                            myParcelableActivity.putExtra("MyParcelableKey", mObjects);
                            itemView.getContext().startActivity(myParcelableActivity);
                            break;
                        case 8:
                            Intent jniActivity = new Intent(itemView.getContext(), JniActivity.class);
                            itemView.getContext().startActivity(jniActivity);
                            break;
                        case 9:
                            mHandler.sendEmptyMessage(ExampleActivity.EVENT_SEND_EXAMPLE_RECEIVED);

                            break;
                        default:
                            break;
                    };

                }
            });
        }

        public void display(Pair<String, String> pair) {
            currentPair = pair;
            name.setText(pair.first);
            description.setText(pair.second);
        }

        public void position(int position) {
            currentPosition = position;
        }
    }


}
