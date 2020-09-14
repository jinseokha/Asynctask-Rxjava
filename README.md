# asynstask-Rxjava 비동기 예제

Android 11에서 AsyncTask가 Deprecated에 대한 글
(https://android-review.googlesource.com/c/platform/frameworks/base/+/1156409)

<br/><br/>

## 현재 프로젝트에 적용된 기능 (AsyncTask는 파일에서 확인가능)

--------------------


<b>viewBinding</b>

- build.gradle (:app)

~~~
android {

    viewBinding {
        enabled = true
    }
}
~~~

<b>Rxjava</b>

- AndroidManifest.xml
~~~
<uses-permission android:name="android.permission.INTERNET"/>
~~~

- build.gradle (:app)

~~~
android {

    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
}

dependencies {
    
    implementation 'io.reactivex.rxjava3:rxjava:3.0.0'
    implementation 'io.reactivex.rxjava3:rxandroid:3.0.0'
}
~~~




