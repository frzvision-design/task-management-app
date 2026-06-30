allprojects {
    repositories {
        maven { url = uri("https://mirror-maven.runflare.com/android/maven2/") }
        maven { url = uri("https://mirror-maven.runflare.com/maven2/") }
        maven { url = uri("https://mirror-maven.runflare.com/gradle-plugins/") }
        maven { url = uri("https://pub-azs.ir/api/mavens/") }
        maven { url = uri("https://maven.myket.ir") }
        maven { url = uri("https://maven.aliyun.com/repository/public") }
        maven { url = uri("https://maven.aliyun.com/repository/central") }
        maven { url = uri("https://maven.aliyun.com/repository/google") }
        maven { url = uri("https://maven.aliyun.com/repository/gradle-plugin") }
        maven { url = uri("https://gradle.jamko.ir") }
        maven { url = uri("https://en-mirror.ir") }
        maven { url = uri("https://google403.ir") }
        google()
        mavenCentral()
    }
    
    configurations.all {
        resolutionStrategy.eachDependency {
            if (requested.group == "com.android.tools.build" && requested.name == "gradle" && requested.version == "8.11.1") {
                useVersion("8.7.3")
                because("AGP 8.11.1 does not exist, using 8.7.3 instead")
            }
            if (requested.group == "com.android.tools.build" && requested.name == "builder" && requested.version == "8.11.1") {
                useVersion("8.7.3")
                because("AGP builder 8.11.1 does not exist, using 8.7.3 instead")
            }
        }
    }
}

val newBuildDir: Directory =
    rootProject.layout.buildDirectory
        .dir("../../build")
        .get()
rootProject.layout.buildDirectory.value(newBuildDir)

subprojects {
    val newSubprojectBuildDir: Directory = newBuildDir.dir(project.name)
    project.layout.buildDirectory.value(newSubprojectBuildDir)
}
subprojects {
    project.evaluationDependsOn(":app")
}

tasks.register<Delete>("clean") {
    delete(rootProject.layout.buildDirectory)
}
