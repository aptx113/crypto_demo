package dependencyLibs

import dependencyVersions.AndroidTestDependencyVersions.ESPRESSO_VERSION
import dependencyVersions.AndroidTestDependencyVersions.TEST_EXT_JUNIT_VERSION
import dependencyVersions.AndroidTestDependencyVersions.TEST_EXT_VERSION

/**
 * All the Project Android Test dependencies are declared here.
 * These can be used across the Project
 */
object Espresso {
    const val CORE = "androidx.test.espresso:espresso-core:${ESPRESSO_VERSION}"
    const val CONTRIB = "androidx.test.espresso:espresso-contrib:${ESPRESSO_VERSION}"
    const val IDLING = "androidx.test.espresso.idling:idling-net:${ESPRESSO_VERSION}"
    const val INTENTS = "androidx.test.espresso:espresso-intents:${ESPRESSO_VERSION}"
}

object TestEx {
    const val JUNIT = "androidx.test.ext:junit:${TEST_EXT_JUNIT_VERSION}"
    const val TRUTH = "androidx.test.ext:truth:${TEST_EXT_VERSION}"
}

object AndroidTestLibraries {
    val androidTestLibraries = arrayListOf<String>().apply {
        add(Arch.CORE)
        add(Coroutines.TESTING)
        add(Espresso.CORE)
        add(Espresso.CONTRIB)
        add(Espresso.IDLING)
        add(Espresso.INTENTS)
        add(Hilt.TESTING)
        add(Mockk.UNIT_TEST)
        add(Mockk.INSTRUMENTAL_TEST)
        add(Navigation.NAV_TESTING)
        add(TestEx.JUNIT)
        add(TestEx.TRUTH)
        add(Truth.TRUTH)
    }
}
