package com.github.solareye.subotemplate.app

fun mainActivity(
    packageName: String,
    modulePackageName: String,
    featureName: String,
    entityName: String,
) = """
package $packageName

import ru.vtb.smb.core_kit.BuildType
import ${modulePackageName}.integration.${featureName}Feature
import ${modulePackageName}.BuildConfig
import ru.vtb.smb.integration_library.FeatureProvider
import ru.vtb.smb.subo_demo.SuboDemoActivity

class ${entityName}Activity : SuboDemoActivity() {

    override val featureProvider: FeatureProvider
        get() = ${featureName}Feature

    override var isMockedData = BuildConfig.BUILD_TYPE == "mocked"

    override var buildType = when (BuildConfig.BUILD_TYPE) {
        "k3"      -> BuildType.K3
        "release" -> BuildType.RELEASE
        else      -> BuildType.CUSTOM("", "")
    }

}
"""