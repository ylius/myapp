package com.example.myskype;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.entities.User;
import com.example.myapp.R;

public class LoginSkypeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_skype);

        Bundle bundle = getIntent().getExtras();
//        String username = bundle.getString("username");
        User user = (User) bundle.getSerializable("user");

        Log.i("LoginSkypeActivity", "Username is " + user.getName()
                + ". User ID is " + user.getId());

        TextView txtShow = (TextView) findViewById(R.id.txt_show);
        txtShow.setText("Username is " + user.getName() + ". User ID is " + user.getId());
    }
}
