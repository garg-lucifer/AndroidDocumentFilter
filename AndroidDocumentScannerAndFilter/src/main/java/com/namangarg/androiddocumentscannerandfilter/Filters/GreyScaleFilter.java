package com.namangarg.androiddocumentscannerandfilter.Filters;

import android.graphics.Bitmap;

import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class GreyScaleFilter {
    public static Bitmap getGreyFilteredImage(Bitmap bitmap){
        Mat mat = new Mat(bitmap.getWidth(), bitmap.getHeight(), CvType.CV_8UC1);
        Utils.bitmapToMat(bitmap, mat);
        Imgproc.cvtColor(mat, mat, Imgproc.COLOR_RGB2GRAY);
        Bitmap result = Bitmap.createBitmap(mat.cols(),mat.rows(),Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(mat, result);
        mat.release();
        return result;
    }
}
