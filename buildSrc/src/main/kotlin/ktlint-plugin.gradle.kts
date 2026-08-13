plugins {
    id("com.pinterest.ktlint") version "12.1.2"
}

ktlint {
    version.set("12.1.2")
    debug.set(false)
    verbose.set(false)
    outputToConsole.set(true)
    outputColorName.set("RED")
    filter {
        exclude { project ->
            project.file.path.contains("build/generated")
        }
    }
}
