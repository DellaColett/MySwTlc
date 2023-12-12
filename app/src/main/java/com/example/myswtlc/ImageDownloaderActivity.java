package com.example.myswtlc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageDownloaderActivity extends AppCompatActivity {
    private static final String DEBUG_TAG = "HttpImageExample";
    private ImageView myImage;
    private EditText urlText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_downloader);
        urlText = findViewById(R.id.edit_image);
        myImage = findViewById(R.id.image_load);
    }

    public void DownloadImageClick(View v) {
        String ImageUrlString = urlText.getText().toString();
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connMgr!= null) {
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                new DownloadImageTask().execute(ImageUrlString);
            } else {
                Toast.makeText(getApplicationContext(),"No network connection available.",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... urls) {
            try {
                Toast.makeText(getApplicationContext(),"Async",Toast.LENGTH_SHORT).show();
                return downloadImage(urls[0]);
            } catch (IOException e) {
                return null;
            }
        }
        @Override
        protected void onPostExecute(Bitmap result) {
            if (result!=null){
                myImage.setImageBitmap(result);
            } else {
                Toast.makeText(getApplicationContext(),"Unavailable image. Check the URL field.",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private Bitmap downloadImage(String myurl) throws IOException {
        InputStream is = null;
        try {
            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();
            int response = conn.getResponseCode();
            Log.d(DEBUG_TAG, "The response is: " + response);
            is = conn.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            return bitmap;
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }
}
