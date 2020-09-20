package com.example.myapp;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class AlbumActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView txtTitle;
    private String[] titles;
    private int[] photos;
    private int index;
    private MediaPlayer mediaPlayer;
    private ImageView imgPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        initView();
        initData();
    }

    private void initData() {
        photos = new int[]{R.drawable.img_photo_1, R.drawable.img_photo_2, R.drawable.img_photo_3,
                R.drawable.img_photo_4, R.drawable.img_photo_5};
        titles = new String[]{"Photo 1", "Photo 2", "Photo 3", "Photo 4", "Photo 5"};
        index = 0;
        updatePhotoAndTitle();
    }

    private void initView() {
        // Use alt + enter to show hints
        // Use alt + cmd + f to make a variable become a class variable
        imgPhoto = (ImageView) findViewById(R.id.img_photo);
        txtTitle = (TextView) findViewById(R.id.txt_title);
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.music);
        findViewById(R.id.btn_prev).setOnClickListener(this); // Any View can setOnClickListener
        findViewById(R.id.btn_next).setOnClickListener(this);
        findViewById(R.id.txt_play).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_prev:
                index = index == 0 ? photos.length - 1 : index - 1;
                updatePhotoAndTitle();
                break;
            case R.id.btn_next:
                index = index == photos.length - 1 ? 0 : index + 1;
                updatePhotoAndTitle();
                break;
            case R.id.txt_play:
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                } else {
                    mediaPlayer.start();
                }
                break;
        }
    }

    private void updatePhotoAndTitle() {
        imgPhoto.setImageResource(photos[index]);
        txtTitle.setText(titles[index]);
    }
}
