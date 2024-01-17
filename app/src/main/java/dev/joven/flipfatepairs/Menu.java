package dev.joven.flipfatepairs;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

public class Menu extends AppCompatActivity {

    private VideoView splash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        getWindow().setFlags(1024, 1024);

        splash = findViewById(R.id.menuView);
        Uri splashFile = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.menufinal);
        splash.setVideoURI(splashFile);

        // Set a loop for the video
        splash.setOnPreparedListener(mediaPlayer -> {
            mediaPlayer.setLooping(true);
        });

        Button startGameButton = findViewById(R.id.StartGame);
        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start the MainContent activity
                Intent intent = new Intent(Menu.this, Policy_Activity.class);
                startActivity(intent);
            }
        });

        Button startPolicyButton = findViewById(R.id.Policy);
        startPolicyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start the MainContent activity
                Intent intent = new Intent(Menu.this, Policy_Activity.class);
                startActivity(intent);
            }
        });

        Button startExitButton = findViewById(R.id.Exit);
        startExitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start the MainContent activity
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Start the video when the activity is resumed
        splash.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Pause the video when the activity is paused
        splash.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Stop the video when the activity is destroyed
        splash.stopPlayback();
    }
}
