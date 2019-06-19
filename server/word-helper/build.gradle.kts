plugins {
    kotlin("jvm")
}

kotlinProject()

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    testImplementation("junit:junit:4.12")
}