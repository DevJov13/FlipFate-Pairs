package dev.joven.flipfatepairs;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainContent extends AppCompatActivity {
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_content);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });



        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);



        getWindow().setFlags(1024, 1024);

        WebView contentapp = findViewById(R.id.GameContent);
        contentapp.setWebViewClient(new WebViewClient());
        contentapp.getSettings().setJavaScriptEnabled(true);
        contentapp.loadUrl("file:///android_asset/index.html");

        ImageView startInfoImageView = findViewById(R.id.Info);

        startInfoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start the Policy_Activity activity
               // Intent intent = new Intent(MainContent.this, Policy_Activity.class);
                //startActivity(intent);
                showInfoAlertDialog();
            }
        });
    }

    private void showInfoAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        View dialogView = LayoutInflater.from(this).inflate(R.layout.consent, null);
        WebView userConsent = dialogView.findViewById(R.id.userConsent);

        String privacyPolicyURL = "file:///android_asset/userconsent.html";
        userConsent.setWebViewClient(new WebViewClient());
        userConsent.loadUrl(privacyPolicyURL);

        builder.setTitle("");
        builder.setView(dialogView);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // Continue with the operation (if needed)
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
