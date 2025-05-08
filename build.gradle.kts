plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.hilt) apply false // assuming you define this in your version catalog
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.21"
}