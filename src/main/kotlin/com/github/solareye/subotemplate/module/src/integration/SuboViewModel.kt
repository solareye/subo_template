package com.github.solareye.subotemplate.module.src.integration

fun suboViewModel(
    packageName: String,
    featureName: String,
) = """
package $packageName.integration

import $packageName.navigation.FeatureRouter
import ru.vtb.smb.subo_common.ui.base.BaseViewModel
import javax.inject.Inject

class ${featureName}ViewModel @Inject constructor(
    private val router: FeatureRouter,
) : BaseViewModel(router) {

    fun showLaunchScreen() {
        router.showMain()
    }
}
""".trimIndent()