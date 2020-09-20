package com.example.myskype;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.entities.User;
import com.example.myapp.R;

public class MyskypeActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myskype);
        // The difference btw Log.i, Log.w and Log.e is the text color.
        Log.i(TAG, "onCreate() was called.");

        initUI();
    }

    private void initUI() {
        findViewById(R.id.btn_skype_user).setOnClickListener(this);
        findViewById(R.id.btn_ms_user).setOnClickListener(this);
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart() was called.");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.w(TAG, "onResume() was called.");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause() was called.");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop() was called.");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart() was called.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy() was called.");
    }

    @Override
    public void onClick(View v) {
        User user = new User();
        user.setId(8008001);
        user.setName("JohnSmith");

        Intent intent = new Intent();
//        intent.putExtra("username", "MariahCarey");
        intent.putExtra("user", user);

        switch (v.getId()) {
            case R.id.btn_skype_user:
                intent.setClass(getApplicationContext(), LoginSkypeActivity.class);
                break;
            case R.id.btn_ms_user:
                intent.setClass(getApplicationContext(), LoginMSActivity.class);
                break;
        }
        this.startActivity(intent); // Or startActivity(intent);
    }
}
