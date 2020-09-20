package com.example.myskype;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.example.myapp.R;

public class LoginMSActivity extends AppCompatActivity {

    private static final String TAG = "LoginMSActivity";
    private static final String SP_PHONE = "sp_phone";
    private static final String SP_PWD = "sp_pwd";
    private static final String SP_IS_REM = "sp_is_rem";
    private EditText etPhone;
    private EditText etPwd;
    private CheckBox cbRem;
    private SharedPreferences sharedPreferences;
    private boolean isChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_ms);

        initUI();
        initData();
    }

    private void initData() {
        // We can use SharedPreferences to both store and read data.
        // Read data in config file.
        if (sharedPreferences == null) {
            sharedPreferences =
                    getApplicationContext().getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        // If cannot get phone, set it as "" (a default value).
        etPhone.setText(sharedPreferences.getString(SP_PHONE, ""));
        etPwd.setText(sharedPreferences.getString(SP_PWD, ""));

        isChecked = sharedPreferences.getBoolean(SP_IS_REM, false);
        cbRem.setChecked(isChecked);
    }

    private void initUI() {
        // Do not need to cast after Android Studio 3.0
        etPhone = (EditText) findViewById(R.id.et_phone);
        etPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                // After Phone changed, record phone.
                if (isChecked) {
                    if (sharedPreferences == null) {
                        sharedPreferences = getApplicationContext().getSharedPreferences("config",
                                Context.MODE_PRIVATE);
                    }
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(SP_PHONE, etPhone.getText().toString());
                    editor.commit();
                }
            }
        });

        etPwd = (EditText) findViewById(R.id.et_pwd);
        etPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                // After Password changed, record password.
                if (isChecked) {
                    if (sharedPreferences == null) {
                        sharedPreferences = getApplicationContext().getSharedPreferences("config",
                                Context.MODE_PRIVATE);
                    }
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(SP_PWD, etPwd.getText().toString());
                    editor.commit();
                }
            }
        });

        cbRem = (CheckBox) findViewById(R.id.cb_rem);
        cbRem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                LoginMSActivity.this.isChecked = isChecked;
                LoginMSActivity.this.isChecked = isChecked;
                Log.d(TAG, "Check box is checked: " + isChecked);
                if (isChecked) { // Execute only at the moment when the check box is checked.
                    if (sharedPreferences == null) {
                        // "config" is the file name. We store data into this file, and this file
                        // is in the Android system.
                        // Context.MODE_PRIVATE is the data read permission, means outside app
                        // cannot read the data.
                        sharedPreferences = getApplicationContext().getSharedPreferences("config",
                                Context.MODE_PRIVATE);
                    }

                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    // Modify data
                    editor.putString(SP_PHONE, etPhone.getText().toString());
                    editor.putString(SP_PWD, etPwd.getText().toString());
                    editor.putBoolean(SP_IS_REM, isChecked);

                    // Commit data change
                    editor.commit();
                }
            }
        });
    }
}
