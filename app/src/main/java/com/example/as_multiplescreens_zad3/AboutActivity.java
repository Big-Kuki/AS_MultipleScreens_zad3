package com.example.as_multiplescreens_zad3;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {

    private static final String TAG = "STOS_TEST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "AboutActivity -> onCreate");
        setContentView(R.layout.activity_about);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "AboutActivity -> onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "AboutActivity -> onDestroy");
    }
}