package com.github.solareye.subotemplate.module.navigation

fun screens(
    packageName: String,
) = """
package $packageName.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.vtb.smb.subo_common.navigation.DialogScreen
import ru.vtb.smb.subo_common.ui.choose_sheet.SelectionFragment

object Screens {

    fun main() =
        FragmentScreen("MainFragment") {
            ToDo()
        }

}
""".trimIndent()