package com.github.solareye.subotemplate.module.src.navigation

fun screens(
    packageName: String,
) = """
package $packageName.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import $packageName.ui.main.MainFragment

object Screens {

    fun main() =
        FragmentScreen("MainFragment") {
            MainFragment.newInstance()
        }

}
""".trimIndent()