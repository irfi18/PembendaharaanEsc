package com.example.asus.pembendaharaanesc;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LoadingActivity extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(LoadingActivity.this,MainActivity.class);
                LoadingActivity.this.startActivity(mainIntent);
                LoadingActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
