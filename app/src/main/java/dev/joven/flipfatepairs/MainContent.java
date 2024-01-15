package dev.joven.flipfatepairs;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainContent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_content);

        getWindow().setFlags(1024, 1024);

        WebView contentapp = findViewById(R.id.GameContent);
        contentapp.setWebViewClient(new WebViewClient());
        contentapp.getSettings().setJavaScriptEnabled(true);
        contentapp.loadUrl("file:///android_asset/index.html");


    }
}