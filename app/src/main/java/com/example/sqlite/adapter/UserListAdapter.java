package com.example.sqlite.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapp.R;
import com.example.sqlite.db.domain.User;

import org.w3c.dom.Text;

import java.util.List;

public class UserListAdapter extends BaseAdapter {
    private final Context context;
    private final List<User> userList;

    public UserListAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }
    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int position) {
        return userList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return userList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_user, null);
        }
//        获取控件
        TextView tvName = (TextView) convertView.findViewById(R.id.tv_name);
        TextView tvDes = (TextView) convertView.findViewById(R.id.tv_des);

//        设置数据
        tvName.setText(userList.get(position).getName());
        tvDes.setText(userList.get(position).getDes());
        return convertView;
    }
}
