package com.namangarg.androiddocumentscannerandfilter.Filters;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;

import com.namangarg.androiddocumentscannerandfilter.Helper.Clahe;

import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ShadowRemovelFilter {

    public MyCallBack callBack;

    public interface MyCallBack<Bitmap>{
        void onComplete(Bitmap bitmap);
    }

    public static void getShadowFilteredImage(Bitmap bit_map, final ShadowRemovelFilter.MyCallBack<Bitmap> callBack){

        Clahe.getClaheImage(bit_map, new Clahe.CallBack<Bitmap>() {
            @Override
            public void onComplete(final Bitmap pre_processed) {
                Executor executor = Executors.newSingleThreadExecutor();
                final Handler handler = new Handler(Looper.getMainLooper());
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
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

                        final Bitmap res_ult = Bitmap.createBitmap(srcArry.cols(),srcArry.rows(),Bitmap.Config.ARGB_8888);
                        Utils.matToBitmap(result_norm, res_ult);

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                callBack.onComplete(res_ult);
                            }
                        });
                    }
                });
            }
        });
    }
}
