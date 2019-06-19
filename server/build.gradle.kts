plugins {
    base
    kotlin("jvm") version "1.3.31" apply false
}

allprojects {
    group = "tech.klopper"
    version = "1.0"

    repositories {
        jcenter()
        mavenCentral()
    }
}

dependencies {
    // Make the root project archives configuration depend on every sub-project
    subprojects.forEach {
        archives(it)
    }
}
