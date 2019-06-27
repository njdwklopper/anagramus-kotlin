import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    id("org.springframework.boot") version "2.1.5.RELEASE"
    id("io.spring.dependency-management") version "1.0.7.RELEASE"
    kotlin("jvm")
    kotlin("plugin.spring") version "1.3.31"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    implementation("io.springfox:springfox-swagger2:2.7.0")
    implementation("io.springfox:springfox-swagger-ui:2.7.0")

    implementation("com.google.firebase:firebase-admin:6.8.1")

    compile(project(":word-helper"))

    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.1.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}

tasks.bootRun {
    args = mutableListOf(
        "--spring.config.additional-location=/opt/apps/anagramus/"
    )
    jvmArgs = mutableListOf(
        "-Dlogging.level.org.springframework=DEBUG",
        "-Dlogging.level.tech.klopper.anagramus=DEBUG"
    )
}

buildscript {
    repositories {
        gradlePluginPortal()
    }
    dependencies {
        classpath("gradle.plugin.com.palantir.gradle.docker:gradle-docker:0.13.0")
    }
}

//TODO convert following gradle copy pasta into kotlin dsl
////apply plugin: 'com.palantir.docker'
//plugins {
//    kotlin("com.palantir.docker")
//}
//tasks.withType<Copy>{
//    dependsOn(bootJar) bootJar
//            from(zipTree(tasks.bootJar.outputs.files.singleFile))
//    into("build/dependency")
//}
//task unpack(type: Copy) {
//    dependsOn bootJar
//            from(zipTree(tasks.bootJar.outputs.files.singleFile))
//    into("build/dependency")
//}
//docker {
//    name "${project.group}/${bootJar.baseName}"
//    copySpec.from(tasks.unpack.outputs).into("dependency")
//    buildArgs(['DEPENDENCY': "dependency"])
//}
