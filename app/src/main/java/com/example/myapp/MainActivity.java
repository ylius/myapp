package com.example.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.myskype.MyskypeActivity;
import com.example.sqlite.activity.SqliteActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_album).setOnClickListener(this);
        findViewById(R.id.btn_quotes).setOnClickListener(this);
        findViewById(R.id.btn_myskype).setOnClickListener(this);
        findViewById(R.id.btn_sqlite).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.btn_album:
                intent = new Intent(this, AlbumActivity.class);
                break;
            case R.id.btn_quotes:
                intent = new Intent(this, QuotesActivity.class);
                break;
            case R.id.btn_myskype:
                intent = new Intent(this, MyskypeActivity.class);
                break;
            case R.id.btn_sqlite:
                intent = new Intent(this, SqliteActivity.class);
                break;
        }
        startActivity(intent);
    }
}
