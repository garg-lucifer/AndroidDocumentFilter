package com.namangarg.androiddocumentscannerandfilter.Helper;

import android.graphics.Bitmap;

import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class Clahe {

    public static Bitmap getClaheImage(Bitmap bitmap){
        Mat srcArry = new Mat(bitmap.getWidth(),bitmap.getHeight(), CvType.CV_8UC1);
        Utils.bitmapToMat(bitmap, srcArry);

        if (srcArry.channels() >= 3) {
            Mat channel = new Mat();
            Imgproc.cvtColor(srcArry, srcArry, Imgproc.COLOR_BGR2Lab);
            Core.extractChannel(srcArry, channel, 0); // extract L channel from Lab Image

            // apply CLAHE algorithm on L channel
            org.opencv.imgproc.CLAHE clahe = Imgproc.createCLAHE();
            clahe.setClipLimit(1);
            clahe.apply(channel, channel);

            // insert it back into the image
            Core.insertChannel(channel, srcArry, 0);
            // convert image back to BGR
            Imgproc.cvtColor(srcArry, srcArry, Imgproc.COLOR_Lab2BGR);

            channel.release();
        }

        Bitmap result = Bitmap.createBitmap(srcArry.cols(),srcArry.rows(),Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(srcArry, result);
        return result;
    }
}
