#-keepattributes SourceFile,LineNumberTable
#-keep class ** {*;}

-keep class com.mss.network.model.** {*;}
-keep class com.mss.core.network.*.model.** {*;}

-dontwarn org.bouncycastle.**
-dontwarn org.conscrypt.**
-dontwarn org.openjsse.**