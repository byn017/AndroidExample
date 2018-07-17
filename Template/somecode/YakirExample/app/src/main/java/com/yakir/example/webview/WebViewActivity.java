package com.yakir.example.webview;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.yakir.example.ExampleActivity;
import com.yakir.example.R;

/**
 * Created by Yakir Nahum on 6/25/2018.
 */

public class WebViewActivity extends Activity {

    WebView htmlWebView;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view);


        htmlWebView = (WebView)findViewById(R.id.webView1);
        htmlWebView.setWebViewClient(new CustomWebViewClient());
        WebSettings webSetting = htmlWebView.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setDisplayZoomControls(true);

        Button google = (Button) findViewById(R.id.webViewGoogle) ;
        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                htmlWebView.loadUrl("http://www.google.com");

            }
        });

        Button ynet = (Button) findViewById(R.id.webViewYnet) ;
        ynet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                htmlWebView.loadUrl("http://www.ynet.co.il");

            }
        });


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
                AlertDialog.Builder builder = new AlertDialog.Builder(WebViewActivity.this);

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

    private class CustomWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
