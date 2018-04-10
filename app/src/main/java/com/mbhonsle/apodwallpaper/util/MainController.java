package com.mbhonsle.apodwallpaper.util;

import android.graphics.Bitmap;

public interface MainController {

    ApodData getJsonData();
    String getImageUrl();
    Bitmap getImage(String imageUrl);

}
