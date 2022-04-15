package com.github.solareye.subotemplate.app

fun someActivity(
    packageName: String,
    entityName: String,
) = """
package $packageName

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ${entityName}Activity : SuboDemoActivity() {

    override val featureProvider: FeatureProvider
        get() = TaxCalendarFeature

    override var isMockedData = BuildConfig.MOCK

    override var buildType = when (BuildConfig.BUILD_TYPE) {
        "k3"      -> BuildType.K3
        "release" -> BuildType.RELEASE
        else      -> BuildType.CUSTOM("", "")
    }

}
"""