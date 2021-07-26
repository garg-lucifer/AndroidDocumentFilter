package com.namangarg.androiddocumentscannerandfilter.Filters;

import android.graphics.Bitmap;
import android.util.Log;

import com.namangarg.androiddocumentscannerandfilter.Helper.Clahe;

import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

public class ShadowRemovelFilter {

    public static Bitmap getShadowFilteredImage(Bitmap bit_map){
        Bitmap pre_processed = getClaheImage(bit_map);
        Mat srcArry = new Mat(pre_processed.getWidth(), pre_processed.getHeight(), CvType.CV_8UC1);
        Utils.bitmapToMat(pre_processed, srcArry);

        Imgproc.cvtColor(srcArry, srcArry, Imgproc.COLOR_BGR2HSV);

        List<Mat> bgrPlanes = new ArrayList<>();
        List<Mat> list = new ArrayList<>();
        List<Mat> result = new ArrayList<>();

        Core.split(srcArry, bgrPlanes);
        list.add(bgrPlanes.get(2)); // adding the V channel for processing in list

        result.add(0,bgrPlanes.get(0)); // adding H channel in result
        result.add(1, bgrPlanes.get(1)); // adding S channel in result

        // processing the V channel for shadow removal
        for (Mat mat : list) {
            Mat dilated_img = new Mat();
            Mat kernel = Mat.ones(7, 7, CvType.CV_32F);
            Imgproc.dilate(mat, dilated_img, kernel);
            Imgproc.medianBlur(dilated_img, dilated_img, 21);
            Mat diff = new Mat();
            Core.absdiff(mat, dilated_img, diff);
            Core.bitwise_not(diff, diff);
            Mat norm = diff.clone();
            Core.normalize(diff,norm,0,255,Core.NORM_MINMAX,CvType.CV_8UC1);
            result.add(norm); // completely processed --> adding V channel into result
        }

        Mat result_norm = new Mat();
        Core.merge(result, result_norm);

        Imgproc.cvtColor(result_norm, result_norm, Imgproc.COLOR_HSV2BGR); // converting image back to RGB

        Bitmap res_ult = Bitmap.createBitmap(srcArry.cols(),srcArry.rows(),Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(result_norm, res_ult);
        return res_ult;

    }

    private static Bitmap getClaheImage(Bitmap bitmap){
        return Clahe.getClaheImage(bitmap);
    }

}
