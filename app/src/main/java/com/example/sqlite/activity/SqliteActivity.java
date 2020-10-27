package com.example.sqlite.activity;


import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapp.R;
import com.example.sqlite.adapter.UserListAdapter;
import com.example.sqlite.db.dao.UserDao;
import com.example.sqlite.db.domain.User;

import java.util.List;

public class SqliteActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView mUserList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
//        初始化控件
        initUI();
//        初始化数据
        initData();
    }

    private void initData() {
        UserDao userDao = UserDao.getInstance(getApplicationContext());
        List<User> userList = userDao.findAll();
        UserListAdapter adapter = new UserListAdapter(getApplicationContext(), userList);
        mUserList.setAdapter(adapter);
    }

    private void initUI() {
        mUserList = (ListView) findViewById(R.id.lv_user_list);
        findViewById(R.id.btn_add_info).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_add_info) {
            showAlertDialog();
        }
    }

    private void showAlertDialog() {
//        实例化弹框的构造者对象
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        自定义弹框内容，所以要调用builder的create和dialog的setView方法
        final AlertDialog alertDialog = builder.create();
        View view = View.inflate(getApplicationContext(), R.layout.dialog_user_info, null);
        alertDialog.setView(view);
//        显示
        alertDialog.show();

        final EditText etName = (EditText) view.findViewById(R.id.et_name);
        final EditText etDes = (EditText) view.findViewById(R.id.et_des);

//        注册弹框的点击事件
        view.findViewById(R.id.btn_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String des = etDes.getText().toString();
                if (name.length() == 0 || des.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Username and description can not be empty", Toast.LENGTH_SHORT);
                    return;
                }
                if (name.length() > 10 || des.length() > 10) {
                    Toast.makeText(getApplicationContext(), "Username or description cannot contain more than 10 characters", Toast.LENGTH_SHORT);
                    return;
                }
                save2db(name, des);
            }
        });
        view.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

    }

    private void save2db(String name, String des) {
        UserDao userDao = UserDao.getInstance(getApplicationContext());
        User user = new User(name, des);
        userDao.insert(user);
//        初始化数据
        initData();
    }
}