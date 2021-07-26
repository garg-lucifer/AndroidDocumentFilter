package com.namangarg.androiddocumentscannerandfilter.Filters;

import android.graphics.Bitmap;

import com.namangarg.androiddocumentscannerandfilter.Helper.Contrast;

import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.CLAHE;
import org.opencv.imgproc.Imgproc;

public class MagicFilter {
    public static Bitmap getMagicFilteredImage(Bitmap bitmap){

        Mat srcArry = new Mat(bitmap.getWidth(),bitmap.getHeight(), CvType.CV_8UC1);
        Utils.bitmapToMat(bitmap, srcArry);
        double contrast_value = Contrast.getContrastLevel(bitmap);

        if (srcArry.channels() >= 3) {

            // READ RGB color image and convert it to HSV
            Mat channel = new Mat();
            Imgproc.cvtColor(srcArry, srcArry, Imgproc.COLOR_BGR2HSV);

            // Extract the V channel
            Core.extractChannel(srcArry, channel, 2);

            // apply the CLAHE algorithm to the V channel
            CLAHE clahe = Imgproc.createCLAHE();
            clahe.setClipLimit(1);
            clahe.apply(channel, channel);

            // Merge the color planes back into an HSV image
            Core.insertChannel(channel, srcArry, 2);

            // Extract the S channel
            Core.extractChannel(srcArry, channel, 1);

            // apply the CLAHE algorithm to the S channel
            CLAHE clahe2 = Imgproc.createCLAHE();
            clahe2.setClipLimit(1);
            clahe2.apply(channel, channel);

            // Merge the color planes back into an HSV image
            Core.insertChannel(channel, srcArry, 1);

            // convert back to RGB
            Imgproc.cvtColor(srcArry, srcArry, Imgproc.COLOR_HSV2BGR);

            channel.release();
        }
        srcArry.convertTo(srcArry, -1,contrast_value,29);
        Bitmap result = Bitmap.createBitmap(srcArry.cols(),srcArry.rows(),Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(srcArry, result);
        srcArry.release();
        return result;
    }
}
