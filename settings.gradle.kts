pluginManagement {
  repositories {
    google {
      content {
        includeGroupByRegex("""com\.android.*""")
        includeGroupByRegex("""com\.google.*""")
        includeGroupByRegex("androidx.*")
      }
    }
    mavenCentral()
    gradlePluginPortal()
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/kotlin/kotlin/dev/") }
  }
  resolutionStrategy {
    eachPlugin {
      if (requested.id.id == "com.android.application" || requested.id.id == "com.android.library") {
        useVersion("8.10.0")
      }
      if (requested.id.id == "org.jetbrains.kotlin.android" || requested.id.id == "org.jetbrains.kotlin.plugin.compose") {
        useVersion("2.2.0")
      }
      if (requested.id.id == "com.google.dagger.hilt.android") {
        useVersion("2.55")
      }
      if (requested.id.id == "com.google.devtools.ksp") {
        useVersion("2.0.1")
      }
    }
  }
}

dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
  }
}

rootProject.name = "MobileModelLoader"

include(":app")
include(":core")
include(":domain")
include(":data")
include(":runtime")
include(":modelmanager")
include(":chat")
include(":benchmark")
include(":skills")
include(":mcp")
include(":ui-common")
