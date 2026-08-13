buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath "io.gitlab.arturbosch:detekt:${libs.versions.detekt.get()}"
    }
}
