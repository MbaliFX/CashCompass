pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven{
            //the data we'll be collecting will be coming from this url
            url = uri("https://jitpack.io")
        }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        //the data we'll be collecting will be coming from this url
        maven {
            url = uri("https://jitpack.io")
        }
    }
}

rootProject.name = "CashCompassApp"
include(":app")
 