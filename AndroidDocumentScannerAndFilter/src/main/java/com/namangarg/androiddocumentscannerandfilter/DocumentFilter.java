package com.namangarg.androiddocumentscannerandfilter;

import android.graphics.Bitmap;

import com.namangarg.androiddocumentscannerandfilter.Filters.BlackAndWhiteFilter;
import com.namangarg.androiddocumentscannerandfilter.Filters.GreyScaleFilter;
import com.namangarg.androiddocumentscannerandfilter.Filters.LightenFilter;
import com.namangarg.androiddocumentscannerandfilter.Filters.MagicFilter;
import com.namangarg.androiddocumentscannerandfilter.Filters.ShadowRemovelFilter;

import org.opencv.android.OpenCVLoader;

public class DocumentFilter{

    public interface CallBack<Bitmap>{
        void onCompleted(Bitmap bitmap);
    }

    static {
        OpenCVLoader.initDebug();
    }

    public void getMagicFilter(Bitmap bitmap, final CallBack<Bitmap> myCallBack) {
        if(bitmap == null) throw new NullPointerException("Bitmap is Null");
        MagicFilter.getMagicFilteredImage(bitmap, new MagicFilter.CallBack<Bitmap>() {
            @Override
            public void onComplete(Bitmap bitmap) {
                myCallBack.onCompleted(bitmap);
            }
        });
    }

    public void getShadowRemoval(Bitmap bitmap, final CallBack<Bitmap> myCallBack) {
        if(bitmap == null) throw new NullPointerException("Bitmap is Null");
        ShadowRemovelFilter.getShadowFilteredImage(bitmap, new ShadowRemovelFilter.MyCallBack<Bitmap>() {
            @Override
            public void onComplete(Bitmap bitmap) {
                myCallBack.onCompleted(bitmap);
            }
        });
    }

    public void getGreyScaleFilter(Bitmap bitmap, final CallBack<Bitmap> myCallBack) {
        if(bitmap == null) throw new NullPointerException("Bitmap is Null");
        GreyScaleFilter.getGreyFilteredImage(bitmap, new GreyScaleFilter.CallBack<Bitmap>() {
            @Override
            public void onComplete(Bitmap bitmap) {
                myCallBack.onCompleted(bitmap);
            }
        });
    }

    public void getLightenFilter(Bitmap bitmap, final CallBack<Bitmap> myCallBack) {
        if(bitmap == null) throw new NullPointerException("Bitmap is Null");
        LightenFilter.getLightenFilteredImage(bitmap, new LightenFilter.CallBack<Bitmap>() {
            @Override
            public void onComplete(Bitmap bitmap) {
                myCallBack.onCompleted(bitmap);
            }
        });
    }

    public void getBlackAndWhiteFilter(Bitmap bitmap, final CallBack<Bitmap> myCallBack) {
        if(bitmap == null) throw new NullPointerException("Bitmap is Null");
        BlackAndWhiteFilter.getBlackAndWhiteFilteredImage(bitmap, new BlackAndWhiteFilter.CallBack<Bitmap>() {
            @Override
            public void onComplete(Bitmap bitmap1) {
                myCallBack.onCompleted(bitmap1);
            }
        });
    }
}
