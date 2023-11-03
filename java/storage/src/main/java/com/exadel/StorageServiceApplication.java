package com.exadel;


import nu.pattern.OpenCV;
import org.opencv.core.*;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

//package org.opencv;


//、exclude = {DataSourceAutoConfiguration.class}
//@SpringBootApplication
public class StorageServiceApplication
{
//    static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }
static {
    OpenCV.loadLocally();
}

    public static void main(String[] args)
    {



//        SpringApplication.run(StorageServiceApplication.class, args);
        System.out.printf("hello world !!!");
        System.out.println("Welcome to OpenCV " + Core.VERSION);
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat m  = Mat.eye(3, 3, CvType.CV_8UC1);
        System.out.println("m = " + m.dump());

//        Core.addSamplesDataSearchPath();


        Mat  image1 = Imgcodecs.imread("1.jpg" );
//        image1.adjustROI();
        Mat resimg = new Mat();
        resimg.create(400, 500, image1.type());
        Imgproc.resize(image1, resimg, resimg.size());



//        HighGui.imshow("chensong1", image1);
        HighGui.imshow("chensong", resimg);
        HighGui.waitKey(1);
        //        HighGui.imshow("chensong", image1);
//        HighGui.waitKey(1);
        Mat  image2 = Imgcodecs.imread("2.jpg");
        Imgproc.resize(image2, image2, new Size(400, 500));
//        Mat  new_image1 = new Mat(new Size(400, 500), image1.type());


        //开始拼接图片
//        Mat dst;
//        dst.create(height,width+width2,oneMat.type());//创建一个大的矩阵用于存放两张图像
//        //将第一张图和第二张图摆放在合适的位置
//        Mat roi1 = dst(Rect(0,0,width,height));
//        oneMat.copyTo(roi1);
//        Mat roi2 = dst(Rect(width,0,width2,height));
//        twoMat.copyTo(roi2);
//
//        imshow("dst",dst);



        Mat dst = new Mat();
        dst.create(image1.height(), image1.width() *2, image1.type());

        Mat roi1 = dst.adjustROI(0, 0, image1.width(), image1.height());
        roi1.copyTo(image1);
        Mat roi2 = dst.adjustROI(image2.width(), 0, image2.width(), image2.height());
        roi2.copyTo(image2);


        HighGui.imshow("chensong", dst);
        HighGui.waitKey(3);
       // image1.resize

       // cv::resize(oneMat,oneMat,Size(width,height));
    }
}
