package com.example.myapp;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class QuotesActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int GET_DATA_SUCCESS = 101; // Flag of getting data success
    private TextView txtQuote;
    private HttpURLConnection connection;
    private InputStream inputStream;
    private BufferedReader bufferedReader;

    // Use Handler to exchange data btw multi threads.
    // Handler can shuttle btw multi threads.
    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            // If there are multiples messages, use switch to handle different messages.
            if (msg.what == GET_DATA_SUCCESS) {
                String data = msg.getData().getString("data");
                Log.i("MainActivity-Handler", data);
                txtQuote.setText(data);
            }
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes);

        initUI();
        initData();
    }

    private void initUI() {
        txtQuote = (TextView) findViewById(R.id.txt_quote);
        findViewById(R.id.txt_next).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        initData();
    }

    private void initData() {
        // Create a sub thread to get data
        // In Android, we must request internet connection in sub thread, otherwise thread block
        new Thread(new Runnable() {
            @Override
            public void run() {
                String data = getDataFromServer();
                Log.i("MainActivity-SubThread", data);

                // Create a Message object
                Message message = Message.obtain(); // If the Message object exists,
                // use the original Message object; if not, create a new Message object.
                Bundle bundle = new Bundle();
                bundle.putString("data", data);
                message.setData(bundle);
                message.what = GET_DATA_SUCCESS;

                // Send data to main thread
                handler.sendMessage(message);
            }
        }).start();
    }

    private String getDataFromServer() {
        try {
            // Step 1: Create a URL
            URL url = new URL("https://v1.hitokoto.cn/?c=f&encode=text");

            // Step 2: Open connection
            connection = (HttpURLConnection) url.openConnection();

            // Step 3: Deal with result
            if (connection.getResponseCode() == 200) {
                inputStream = connection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuilder sb = new StringBuilder();
                for (String line = ""; (line = bufferedReader.readLine()) != null; ) {
                    sb.append(line);
                }
                return sb.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }

        return "";
    }
}
