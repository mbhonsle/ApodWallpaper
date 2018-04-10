package com.mbhonsle.apodwallpaper.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

/**
 * Created by mak on 12/13/17.
 */

public class ImageHelper {

    static class DownloadTask extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... urls) {
            URL url;
            HttpURLConnection httpURLConnection;

            try {
                url = new URL(urls[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                return BitmapFactory.decodeStream(inputStream);
            } catch (Exception e) {
                Log.e("ERROR:", e.getMessage());
            }
            return null;
        }
    }

    Bitmap fetch(String url) throws ExecutionException, InterruptedException {
        return new DownloadTask().execute(url).get();
    }
}