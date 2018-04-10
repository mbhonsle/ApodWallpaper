package com.mbhonsle.apodwallpaper.util;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;

public class WallPaperUtil {

    private final Context context;
    private WallpaperManager wallpaperManager;

    public WallPaperUtil(Context context) {
        this.context = context;
        this.wallpaperManager = WallpaperManager.getInstance(this.context);
    }

    public void setWallPaper(Bitmap image, DisplayMetrics displayMetrics) {
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        Bitmap bitmap = Bitmap.createScaledBitmap(image, width, height, true);
        wallpaperManager.setWallpaperOffsetSteps(1, 1);
        wallpaperManager.suggestDesiredDimensions(width, height);
        try {
            wallpaperManager.setBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
