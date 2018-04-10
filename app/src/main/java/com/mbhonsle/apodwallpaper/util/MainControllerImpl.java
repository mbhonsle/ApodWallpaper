package com.mbhonsle.apodwallpaper.util;

import android.graphics.Bitmap;
import android.util.Log;

public class MainControllerImpl implements MainController {

    private String serverBaseURl = "";
    private ApodDataService apodDataService;
    private ImageHelper imageHelper;

    public MainControllerImpl(String url) {
        this.serverBaseURl = url;
        this.apodDataService = new ApodDataServiceImpl();
        this.imageHelper = new ImageHelper();
    }

    @Override
    public ApodData getJsonData() {
        return this.apodDataService.getJsonData(this.serverBaseURl);
    }

    @Override
    public String getImageUrl() {
        ApodData jsonData = this.apodDataService.getJsonData(this.serverBaseURl);
        Log.i("MainController", "APOD data object: " + jsonData.toString());
        return jsonData.getUrl();
    }

    @Override
    public Bitmap getImage(String imageUrl) {
        imageUrl = imageUrl.isEmpty() ? getImageUrl() : imageUrl;
        try {
            return this.imageHelper.fetch(imageUrl);
        } catch (Exception e) {
            Log.e("Image Helper Exception", e.getMessage(), e);
        }
        return null;
    }
}
