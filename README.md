# AndroidDocumentFilter
Android Document Filter is an Android Library that makes it simple to add commonly used filters in document image processing, such as Magic Filter, Shadow Remvoal, Black and White Filter, Lighten Color, and GreyScale Filter. It performs all resource-intensive tasks in the background thread.

Screenshots
----------------
<img src="https://github.com/garg-lucifer/AndroidDocumentFilter/blob/master/img1.png">
<img src="https://github.com/garg-lucifer/AndroidDocumentFilter/blob/master/img2.png">

Setup
----------------

First, add jitpack in your build.gradle at the end of repositories:
 ```groovy
repositories {
    // ...       
    maven { url "https://jitpack.io" }
}
```

Then, add the library dependency:
```groovy
implementation 'com.github.garg-lucifer:AndroidDocumentFilter:0.6.0'
```
Usage
----------------
``` java

DocumentFilter documentFilter = new DocumentFilter();

// replace getFilter_Name with the filter you want to use
documentFilter.getFilter_Name(image_bitmap, new DocumentFilter.CallBack<Bitmap>() {
                    @Override
                    public void onCompleted(Bitmap bitmap) {
                        // Do your tasks here with the returned bitmap
                    }
                });

// for example shadow removal
documentFilter.getShadowRemoval(image_bitmap, new DocumentFilter.CallBack<Bitmap>() {
                    @Override
                    public void onCompleted(Bitmap bitmap) {
                        // Do your tasks here with the returned bitmap
                    }
                });
```
Thanks
----------------

Thanks OpenCV for this amazing library. - https://opencv.org/

LICENCE
-----
AndroidDocumentFilter by [Naman Garg] is licensed under a [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0).
