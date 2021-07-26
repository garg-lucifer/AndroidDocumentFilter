package com.namangarg.androiddocumentscannerandfilter.Filters;

import android.graphics.Bitmap;

import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class BlackAndWhiteFilter {

    public static Bitmap getBlackAndWhiteFilteredImage(Bitmap bitmap){
        // adaptive threshold with median blur
        // works best on text written on blank paper instead of ruled ones.
        Mat mat = new Mat(bitmap.getWidth(),bitmap.getHeight(), CvType.CV_8UC1);
        Utils.bitmapToMat(bitmap, mat);
        Imgproc.cvtColor(mat, mat, Imgproc.COLOR_RGB2GRAY);
        Imgproc.medianBlur(mat,mat,5);
        Imgproc.adaptiveThreshold(mat, mat,255,Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C,Imgproc.THRESH_BINARY,11,3);
        Bitmap result = Bitmap.createBitmap(mat.cols(),mat.rows(),Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(mat, result);
        mat.release();
        return result;
    }
}
