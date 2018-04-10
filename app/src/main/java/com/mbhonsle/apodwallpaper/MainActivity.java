package com.mbhonsle.apodwallpaper;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.mbhonsle.apodwallpaper.util.ApodData;
import com.mbhonsle.apodwallpaper.util.MainController;
import com.mbhonsle.apodwallpaper.util.MainControllerImpl;
import com.mbhonsle.apodwallpaper.util.WallPaperUtil;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private Button button;
    private MainController mainController;
    private WallPaperUtil wallpaperUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initServices();
        run();
    }

    private void initViews() {
        this.imageView = findViewById(R.id.imageView);
        this.button = findViewById(R.id.button);
    }

    private void run() {
        ApodData apodData = this.mainController.getJsonData();
        if (apodData.getTitle().equals("default")) {
            Log.w("MainActivity", "Could not get APOD data");
        } else {
            this.button.setVisibility(View.VISIBLE);
            if (apodData.getMedia_type().toLowerCase().equals("video")) {
                handleVideo(apodData);
            } else {
                handleImage(apodData);
            }
        }
    }

    private void handleVideo(ApodData apodData) {
        this.button.setText(R.string.play_video);
        this.imageView.setVisibility(View.GONE);
        this.button.setOnClickListener(new OnClickListenerForVideo(apodData));
    }

    private void handleImage(ApodData apodData) {
        this.button.setText(R.string.set_wallpaper);
        this.imageView.setVisibility(View.VISIBLE);
        Bitmap image = this.mainController.getImage(apodData.getUrl());
        this.imageView.setImageBitmap(image);
        this.button.setOnClickListener(new OnClickListenerForImage(image));
    }

    private DisplayMetrics buildDisplayMetrics() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    @SuppressLint("StringFormatInvalid")
    private void initServices() {
        String url = getString(R.string.apod_base_url, getString(R.string.api_key));
        this.mainController = new MainControllerImpl(url);
        this.wallpaperUtil = new WallPaperUtil(this);
    }

    private class OnClickListenerForVideo implements View.OnClickListener {
        private ApodData apodData;
        public OnClickListenerForVideo(ApodData apodData) {
            this.apodData = apodData;
        }
        @Override
        public void onClick(View v) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(this.apodData.getUrl())));
        }
    }

    private class OnClickListenerForImage implements View.OnClickListener {
        private Bitmap image;
        public OnClickListenerForImage(Bitmap image) {
            this.image = image;
        }
        @Override
        public void onClick(View v) {
            try {
                wallpaperUtil.setWallPaper(image, buildDisplayMetrics());
            } catch (Exception e) {
                Log.e("WallpaperManager", e.getMessage(), e);
            }
        }
    }

}
