package com.github.solareye.subotemplate.module.src.ui.main

fun mainViewState(
    suboModulePackageName: String,
) = """
package $suboModulePackageName.ui.main
import ru.vtb.smb.subo_common.ui.recycler.RecyclerViewModel

internal data class MainViewState(
    val isLoading: Boolean = true,
    val data: Any? = null
)
""".trimIndent()