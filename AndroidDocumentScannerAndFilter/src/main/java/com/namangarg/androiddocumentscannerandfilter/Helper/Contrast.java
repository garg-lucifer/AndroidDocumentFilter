package com.namangarg.androiddocumentscannerandfilter.Helper;

import android.graphics.Bitmap;
import android.util.Log;

import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

public class Contrast {

    private static float[] range = {0, 256};
    static int [] brightnessVal = new int[5];
    static int sum = 0;
    static int max = Integer.MIN_VALUE;
    static int ans = -1;

    public static double getContrastLevel(Bitmap bitmap){

        MatOfFloat histRange = new MatOfFloat(range);
        Mat valueHist = new Mat();

        Mat srcArry = new Mat(bitmap.getWidth(), bitmap.getHeight(), CvType.CV_8UC1);
        Utils.bitmapToMat(bitmap, srcArry);

        if (srcArry.channels() >= 3) {
            Imgproc.cvtColor(srcArry, srcArry, Imgproc.COLOR_BGR2HSV);

            List<Mat> bgrPlanes = new ArrayList<>();
            Core.split(srcArry, bgrPlanes);

            // calculating histogram of only V channel
            Imgproc.calcHist(bgrPlanes, new MatOfInt(2), new Mat(), valueHist, new MatOfInt(256), histRange, false);

            int histW = 512, histH = 400;

            Mat histImage = new Mat( histH, histW, CvType.CV_8UC3, new Scalar( 0,0,0) );
            Core.normalize(valueHist, valueHist, 0, histImage.rows(), Core.NORM_MINMAX);

            float[] bHistData = new float[(int) (valueHist.total() * valueHist.channels())];
            valueHist.get(0, 0, bHistData);

            // the following loop analyzes the histogram and looks for the region in graph with highest concentrated
            // peaks and returns that region as integer.
            // this histogram is divided into five regions.

            for(int i = 1; i < 256; i++ ) {

                int p = Math.round(bHistData[i]);
                int divideBy = 51;

                if (i < 52){
                    sum += p;
                    if (i == 51){
                        brightnessVal[0] = sum/ divideBy;
                        sum = 0;
                    }
                } else if (i < 103){
                    sum += p;
                    if (i == 102){
                        brightnessVal[1] = sum/ divideBy;
                        sum = 0;
                    }
                }else if ( i < 154){
                    sum += p;
                    if (i == 153){
                        brightnessVal[2] = sum/ divideBy;
                        sum = 0;
                    }
                }else if (i <  204){
                    sum += p;
                    if (i == 203){
                        brightnessVal[3] = sum/ divideBy;
                        sum = 0;
                    }
                }else {
                    sum += p;
                    if (i == 255){
                        brightnessVal[4] = sum/ divideBy;
                        sum = 0;
                    }
                }
            }

            for (int i = 0; i < 5;i++){
                if ( max < brightnessVal[i]){
                    max = brightnessVal[i];
                    ans = i;
                }
            }
        }
        return ContrastHelper.getContrastValue(ans);
    }
}
