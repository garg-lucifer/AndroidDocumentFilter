package com.namangarg.androiddocumentscannerandfilter.Filters;

import android.graphics.Bitmap;

import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

public class LightenFilter {
    public static Bitmap getLightenFilteredImage(Bitmap bitmap){
        Mat mat = new Mat(bitmap.getWidth(), bitmap.getHeight(), CvType.CV_8UC1);
        Utils.bitmapToMat(bitmap,mat);
        mat.convertTo(mat, -1,1,40);
        // increases brightness by 40
        Bitmap result = Bitmap.createBitmap(mat.cols(),mat.rows(),Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(mat, result);
        mat.release();
        return result;
    }
}
