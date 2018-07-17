package com.yakir.example.notification;

/**
 * Created by Yakir Nahum on 6/25/2018.
 */

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.yakir.example.R;

import java.util.Arrays;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private final List<String> notifiactionAndDialogList=  Arrays.asList("AlertDialog","Notification","ProgressDialog");


    @Override
    public int getItemCount() {
        return notifiactionAndDialogList.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_cell, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String action = notifiactionAndDialogList.get(position);
        holder.display(action);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        public static final String NOTIFICATION_CHANNEL_ID = "1977";
        private String currentAction;
        private  ProgressDialog progress;

        public MyViewHolder(final View itemView) {
            super(itemView);

            name = ((TextView) itemView.findViewById(R.id.name));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (currentAction.equals("AlertDialog")) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
                        builder.setTitle("AlertDialog");
                        builder.setMessage("Simple AlertDialog example");
                        builder.setPositiveButton("EXIT", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Do nothing but close the dialog
                                dialog.dismiss();
                            }
                        });
                        AlertDialog alert = builder.create();
                        alert.show();


                    } else if (currentAction.equals("Notification")) {
                        int NOTIFICATION_ID = 234;
                        NotificationManager notificationManager = (NotificationManager) itemView.getContext().getSystemService(Context.NOTIFICATION_SERVICE);
                        String CHANNEL_ID = "my_channel_01";
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                            CharSequence name = "my_channel";
                            String Description = "This is my channel";
                            int importance = NotificationManager.IMPORTANCE_HIGH;
                            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
                            mChannel.setDescription(Description);
                            mChannel.enableLights(true);
                            mChannel.setLightColor(Color.RED);
                            mChannel.enableVibration(true);
                            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                            mChannel.setShowBadge(false);
                            notificationManager.createNotificationChannel(mChannel);
                        }
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(itemView.getContext(), CHANNEL_ID)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setContentTitle("My Notification")
                                .setContentText("Yakir example");

                        notificationManager.notify(NOTIFICATION_ID, builder.build());

                    } else if (currentAction.equals("ProgressDialog")) {
                        progress = ProgressDialog.show(itemView.getContext(), "", "ProgressDialog example", true);
                        Thread t = new Thread()
                        {
                            public void run()
                            {
                                try {
                                    Thread.sleep(2000);
                                } catch (Exception e) {
                                }
                                progress.dismiss();
                            }
                        };
                        t.start();
                    }

                }
            });
        }

        public void display(String action ) {
            currentAction = action;
            name.setText(action);
        }
    }

}