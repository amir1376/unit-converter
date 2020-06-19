import java.io.IOException
import java.util.*
import kotlin.properties.Delegates.notNull

val rootExt = ext
plugins {
    `java-library`
    kotlin("jvm") version "1.3.72"
    `maven-publish`
    id("com.jfrog.bintray") version "1.8.5"
}
ext["localRepoDir"] = File(System.getenv("LOCAL_REPO"))
ext["githubUrl"] = "hub.com/amir1376/unit-converter"
val localRepoDir: File by ext
val githubUrl: String by ext

with(File("gradle/external.gradle.kts")) {
    if (exists()) {
        apply(from = this@with.absolutePath)
    }
}
group = "ir.amirabdol"
version = "1.0"
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
        // a local directory that I save all my repos
        maven {
            name = "my-repo"
            localRepoDir.mkdirs()
            if (!localRepoDir.exists()) {
                throw IOException()
            }
            url = localRepoDir.toURI()
        }
    }
}
bintray {
    user = rootExt["bintray_user"].toString()
    key = rootExt["bintray_api_key"].toString()
    pkg.apply {
        repo = "Libs"
        name = rootProject.name
        vcsUrl = githubUrl
        websiteUrl = githubUrl
        setPublications(myMavenPublication.name)
        setLicenses("Apache-2.0")
        version.apply {
            name = rootProject.version.toString()
            released = Date().toString()
        }
    }
}