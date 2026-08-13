plugins {
    id("io.gitlab.arturbosch.detekt")
}

detekt {
    config = files("$projectDir/detekt.yml")
    buildUponDefaultConfig = true
    allRules = false
    basePath = projectDir
}
