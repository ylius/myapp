package com.example.sqlite.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sqlite.db.UserOpenHelper;
import com.example.sqlite.db.domain.User;

import java.util.ArrayList;
import java.util.List;

public class UserDao {

    private final UserOpenHelper userOpenHelper;

    private static UserDao userDao = null;

//    私有构造方法
    private UserDao(Context context) {
        userOpenHelper = new UserOpenHelper(context);
    }

    public static UserDao getInstance(Context context) {
        if (userDao == null) {
            userDao = new UserDao(context);
        }
        return userDao;
    }

//    保存
    public void insert(User user) {
//        实例化数据库对象
        SQLiteDatabase db = userOpenHelper.getWritableDatabase();
//        构造保存的数据
        ContentValues values = new ContentValues();
        values.put("name", user.getName());
        values.put("des", user.getDes());
//        插入数据
        db.insert("user", null, values);
//        关闭数据库
        db.close();
    }

//    获取所有数据
    public List<User> findAll() {
//        实例化数据库对象
        SQLiteDatabase db = userOpenHelper.getWritableDatabase();
        Cursor cursor = db.query("user", new String[] {"_id", "name", "des"}, null, null, null, null, null);
        List<User> userList = new ArrayList<>();
        while (cursor.moveToNext()) {
            User user = new User();
            user.setId(cursor.getInt(0));
            user.setName(cursor.getString(1));
            user.setDes(cursor.getString(2));
            userList.add(user);
        }
//        关闭数据库
        db.close();
        return userList;
    }

}
