import java.io.IOException
import java.util.*
import kotlin.properties.Delegates.notNull

val rootExt = ext
plugins {
    `java-library`
    kotlin("jvm") version "1.3.72"
    `maven-publish`
}
ext["githubUrl"] = "https://github.com/amir1376/unit-converter"
val githubUrl: String by ext

with(File(rootProject.rootDir, "gradle/external.gradle.kts")) {
    if (exists()) {
        apply(from = this@with.absolutePath)
    }
}
group = "ir.amirabdol"
version = "1.01"
repositories {
    mavenCentral()
}
dependencies {
    implementation(kotlin("stdlib-jdk8"))
    testImplementation("junit", "junit", "4.12")
}
configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}
val sourceJar by tasks.creating(Jar::class) {
    dependsOn(JavaPlugin.CLASSES_TASK_NAME)
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allSource)
}
artifacts {
    archives(sourceJar)
}
var myMavenPublication by notNull<MavenPublication>()
publishing {
    publications {
        myMavenPublication = create<MavenPublication>("mavenJava") {
            from(components["java"])
            artifact(sourceJar)
        }
    }
    repositories {
        mavenLocal()
    }
}