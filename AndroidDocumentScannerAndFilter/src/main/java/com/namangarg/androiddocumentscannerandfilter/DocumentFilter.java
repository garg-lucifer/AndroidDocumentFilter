package com.namangarg.androiddocumentscannerandfilter;

import android.graphics.Bitmap;

import com.namangarg.androiddocumentscannerandfilter.Filters.BlackAndWhiteFilter;
import com.namangarg.androiddocumentscannerandfilter.Filters.GreyScaleFilter;
import com.namangarg.androiddocumentscannerandfilter.Filters.LightenFilter;
import com.namangarg.androiddocumentscannerandfilter.Filters.MagicFilter;
import com.namangarg.androiddocumentscannerandfilter.Filters.ShadowRemovelFilter;

import org.opencv.android.OpenCVLoader;

public class DocumentFilter {

    static {
        OpenCVLoader.initDebug();
    }

    public static Bitmap getMagicFilter(Bitmap bitmap){
        return MagicFilter.getMagicFilteredImage(bitmap);
    }

    public static Bitmap getShadowRemoval(Bitmap bitmap){
        return ShadowRemovelFilter.getShadowFilteredImage(bitmap);
    }

    public static Bitmap getGreyScaleFilter(Bitmap bitmap){
        return GreyScaleFilter.getGreyFilteredImage(bitmap);
    }

    public static Bitmap getLightenFilter(Bitmap bitmap){
        return LightenFilter.getLightenFilteredImage(bitmap);
    }

    public static Bitmap getBlackAndWhiteFilter(Bitmap bitmap){
        return BlackAndWhiteFilter.getBlackAndWhiteFilteredImage(bitmap);
    }

}
