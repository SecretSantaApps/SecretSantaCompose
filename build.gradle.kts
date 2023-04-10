buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.3.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.10")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.42")
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.7.10")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.5.3")
        classpath("com.google.gms:google-services:4.3.15")
        val navVersion = "2.5.3"
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$navVersion")
        
        classpath("com.google.firebase:firebase-crashlytics-gradle:2.9.2")
        classpath("com.google.firebase:perf-plugin:1.4.2")
    }
}

tasks.register("installGitHook", Copy::class) {
    from(layout.projectDirectory.file("pre-commit"))
    into(layout.projectDirectory.file(".git/hooks"))
    fileMode = 0b0111101101
}

tasks.getByPath(":app:preBuild").dependsOn(tasks.getByName("installGitHook"))

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}