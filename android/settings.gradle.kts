pluginManagement {
    val flutterSdkPath =
        run {
            val properties = java.util.Properties()
            file("local.properties").inputStream().use { properties.load(it) }
            val flutterSdkPath = properties.getProperty("flutter.sdk")
            require(flutterSdkPath != null) { "flutter.sdk not set in local.properties" }
            flutterSdkPath
        }

    repositories {
        maven { url = uri("https://mirror-maven.runflare.com/android/maven2/") }
        maven { url = uri("https://mirror-maven.runflare.com/maven2/") }
        maven { url = uri("https://mirror-maven.runflare.com/gradle-plugins/") }
        maven { url = uri("https://pub-azs.ir/api/mavens/") }
        maven { url = uri("https://maven.myket.ir") }
        maven { url = uri("https://maven.aliyun.com/repository/public") }
        maven { url = uri("https://gradle.jamko.ir") }
        maven { url = uri("https://en-mirror.ir") }
        maven { url = uri("https://google403.ir") }
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id == "com.android.application") {
                useModule("com.android.tools.build:gradle:8.7.3")
            }
        }
    }

    includeBuild("$flutterSdkPath/packages/flutter_tools/gradle")
}

plugins {
    id("dev.flutter.flutter-plugin-loader") version "1.0.0"
    id("com.android.application") version "8.7.3" apply false
    id("org.jetbrains.kotlin.android") version "2.1.0" apply false
}

include(":app")
