object Deps {
    ///Testing
    const val junit = "junit:junit:${Versions.jUnit}"

    ///Coroutines
    const val coroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    ///Lifecycle
    const val lifecycleViewModel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"

    ///Worker
    const val workRuntime = "androidx.work:work-runtime:${Versions.workers}"
    const val workRuntimeKtx = "androidx.work:work-runtime-ktx:${Versions.workers}"

    ///Hilt
    const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltAndroidCompiler =
        "com.google.dagger:hilt-android-compiler:${Versions.hiltAndroidCompiler}"
    const val hiltCompiler = "androidx.hilt:hilt-compiler:${Versions.hiltCompiler}"
    const val hiltWork = "androidx.hilt:hilt-work:${Versions.hiltWork}"

    ///Data store
    const val dataStore = "androidx.datastore:datastore-preferences:${Versions.dataStore}"
}

object CustomDeps {
    const val scalableSP = "com.intuit.sdp:sdp-android:${Versions.sSPDP}"
    const val scalableDP= "com.intuit.ssp:ssp-android:${Versions.sSPDP}"
}

object Versions {
    const val gradlePlugin = "7.4"
    const val kotlin = "1.6.10"
    const val jUnit = "4.13.2"
    const val systemUiController = "0.24.3-alpha"
    const val customSlider = "0.1.4"
    const val coroutines = "1.6.0"
    const val lifecycle = "2.4.1"
    const val libSu = "4.0.3"
    const val workers = "2.7.1"
    const val dataStore = "1.0.0"
    const val hiltWork = "1.0.0"
    const val hilt = "2.42"
    const val hiltAndroidCompiler = "2.42"
    const val hiltCompiler = "1.0.0"
    const val sSPDP="1.0.6"

}

object ConfigData {
    const val compileSdkVersion = 32
    const val minSdkVersion = 28
    const val targetSdkVersion = 32
    const val versionCode = 1
    const val versionName = "1.0"
    const val appId = "com.tuly.userdatabase"

}