# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/kang/Library/Android/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
-verbose
-keep class android.support.design.widget.** { *; }
-keep interface android.support.design.widget.** { *; }

-dontwarn javax.annotation.**
-dontwarn javax.inject.**
# OkHttp3
-dontwarn okhttp3.logging.**
-keep class okhttp3.internal.**{*;}
-dontwarn okio.**
# Retrofit
# Platform calls Class.forName on types which do not exist on Android to determine platform.
-dontnote retrofit2.Platform
# Platform used when running on RoboVM on iOS. Will not be used at runtime.
-dontnote retrofit2.Platform$IOS$MainThreadExecutor
# Platform used when running on Java 8 VMs. Will not be used at runtime.
-dontwarn retrofit2.Platform$Java8
# Retain generic type information for use by reflection by converters and adapters.
-keepattributes Signature
# Retain declared checked exceptions for use by a Proxy instance.
-keepattributes Exceptions
# Gson
-keep class com.google.gson.stream.** { *; }
-keepattributes EnclosingMethod
################entity和result
-keep class com.malalaoshi.android.malapad..data.entity.** {*;}
-keep class com.malalaoshi.android.malapad..data.api.param.** {*;}
-keep class com.malalaoshi.android.malapad..data.api.response.** {*;}
-keep class com.malalaoshi.android.core.entity.** {*;}
-keep class com.malalaoshi.android.core.network.response.**{*;}