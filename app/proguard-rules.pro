#-keepattributes SourceFile,LineNumberTable
#-keep class ** {*;}
-dontobfuscate
-keep class com.mss.core.network.*.model.** {*;}

-dontwarn org.bouncycastle.**
-dontwarn org.conscrypt.**
-dontwarn org.openjsse.**