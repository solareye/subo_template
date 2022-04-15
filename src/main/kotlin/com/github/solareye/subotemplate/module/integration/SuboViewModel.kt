package com.github.solareye.subotemplate.module.integration

fun suboViewModel(
    packageName: String,
    featureName: String,
) = """
package $packageName.integration

import $packageName.navigation.FeatureRouter
import ru.vtb.smb.subo_common.ui.base.BaseViewModel
import javax.inject.Inject

class ${featureName.capitalize()}ViewModel @Inject constructor(
    private val router: FeatureRouter,
) : BaseViewModel(router) {

    fun showLaunchScreen() {
        router.showMain()
    }
}
""".trimIndent()