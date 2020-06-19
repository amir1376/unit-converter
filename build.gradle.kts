plugins {
    `java-library`
    kotlin("jvm") version "1.3.72"
    `maven-publish`
}
ext["localRepoDir"] = File(System.getenv("LOCAL_REPO"))
val localRepoDir: File by ext
with(File("gradle/external.gradle.kts")) {
    if (exists()) { apply(from = this@with.absolutePath) }
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
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allSource)
}
publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            artifact(sourceJar)
        }
        val function: (RepositoryHandler).() -> Unit = {
            maven {
                name = "my-repo"
                localRepoDir.mkdirs()
                url = localRepoDir.toURI()
            }
        }
        repositories(function)
    }
}