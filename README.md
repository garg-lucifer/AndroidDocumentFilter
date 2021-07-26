# AndroidDocumentFilter
Android Document Filter is an Android Library built on top of OpenCV that provides various filters for document scanning.

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
```
Thanks
----------------

Thanks OpenCV for this library. - https://opencv.org/ and

LICENCE
-----
AndroidDocumentFilter by [Naman Garg] is licensed under a [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0).
