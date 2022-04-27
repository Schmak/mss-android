import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import extensions.printSuiteResult
import io.gitlab.arturbosch.detekt.Detekt
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import utils.version.type

buildscript {
    repositories {
        google()
    }
    dependencies {
        classpath(Deps.GradlePlugins.android)
        classpath(Deps.GradlePlugins.hilt)
    }
}

plugins {
    kotlin("android") version Versions.kotlin apply false
    id(Deps.GradlePlugins.Detekt.core) version Versions.GradlePlugin.detekt
    id(Deps.GradlePlugins.dependencyUpdates) version Versions.GradlePlugin.dependencyUpdates
}

allprojects {
    apply {
        plugin(Deps.GradlePlugins.Detekt.core)
    }
    tasks {
        withType<KotlinCompile> {
            kotlinOptions.allWarningsAsErrors = true
            kotlinOptions.freeCompilerArgs += "-opt-in=kotlin.RequiresOptIn"
        }
        withType<Test> {
            outputs.upToDateWhen { false }
            useJUnitPlatform()
            testLogging {
                events = setOf(
                    TestLogEvent.SKIPPED,
                    TestLogEvent.FAILED
                )
            }
            afterSuite(printSuiteResult)
        }
        withType<Detekt> {
            allRules = true
            config.setFrom("$rootDir/detekt.yml")
            parallel = true
            basePath = rootDir.toString()
            exclude("build/")
            reports {
                html.required.set(false)
                sarif.required.set(false)
                txt.required.set(false)
                xml.required.set(false)
            }
        }
        withType<DependencyUpdatesTask> {
            rejectVersionIf {
                "${candidate.group}:${candidate.module}" in utils.version.ignoredModules ||
                        currentVersion.type > candidate.version.type
            }
        }
    }
    dependencies {
        detektPlugins(Deps.GradlePlugins.Detekt.formatting)
    }
}