# AndroidDocumentFilter
Android Document Filter is an Android Library that allows you to easily add commonly used filters in document image processing, such as Magic Filter, Shadow Remvoal, Black and White Filter, Lighten Color, and GreyScale Filter with just a single line of code.

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
implementation 'com.github.garg-lucifer:AndroidDocumentFilter:0.1.0'
```
Usage
----------------
``` java
// magic filter
Bitmap result = DocumentFilter.getMagicFilter(bitmap);
// shadow removal
Bitmap result = DocumentFilter.getShadowRemoval(bitmap);
// black & white filter
Bitmap result = DocumentFilter.getBlackAndWhiteFilter(bitmap);
// lighten color
Bitmap result = DocumentFilter.getLightenFilter(bitmap);
// greyScale filter
Bitmap result = DocumentFilter.getGreyScaleFilter(bitmap);
```
Thanks
----------------

Thanks OpenCV for this amazing library. - https://opencv.org/

LICENCE
-----
AndroidDocumentFilter by [Naman Garg] is licensed under a [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0).
