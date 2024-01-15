package dev.joven.flipfatepairs;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.VideoView;

public class LoadingScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);

        getWindow().setFlags(1024,1024);

        VideoView splash=findViewById(R.id.videoView);
        Uri splashFile =Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.compressload);
        splash.setVideoURI(splashFile);


        splash.start();
        splash.setOnCompletionListener(mediaPlayer -> {
            splash.stopPlayback();
            splash.setVisibility(View.GONE);

            Intent intent = new Intent(LoadingScreen.this, Policy_Activity.class);
            startActivity(intent); // Use 'intent' instead of 'Intent'
            finishAffinity();
        });
    }

}