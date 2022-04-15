package com.github.solareye.subotemplate.module.integration

fun suboFeature(
    packageName: String,
    featureName: String,
) = """
package $packageName.integration

import com.github.terrakok.cicerone.Router
import $packageName.di.components.AppComponent
import $packageName.di.components.DaggerAppComponent
import $packageName.di.components.FeatureComponent
import ru.vtb.smb.core_kit.ResourceProvider
import ru.vtb.smb.integration_library.*
import ru.vtb.smb.subo_common.di.SuboCommonDi
import ru.vtb.smb.subo_common.di.components.SuboCommonDependencies
import ru.vtb.smb.subo_common.di.modules.CoreModule

object ${featureName.capitalize()}Feature : FeatureProvider {


    var appComponent: AppComponent? = null
    private var featureComponent: FeatureComponent? = null

    fun getFeatureComponent(): FeatureComponent? {
        appComponent?.let {
            if (featureComponent == null) {
                featureComponent = it.featureComponent().build()
                SuboCommonDi.init(object: SuboCommonDependencies {
                    override fun resourceProvider(): ResourceProvider {
                        return it.resourceProvider()
                    }

                    override fun router(): Router {
                        return it.router()
                    }
                })

            }
            return featureComponent
        } ?: return null
    }

    override val systemName: Array<String>
        get() = arrayOf()

    override fun init(coreAppDependencies: CoreAppDependencies) {
        appComponent = DaggerAppComponent
            .builder()
            .coreModule(CoreModule(coreAppDependencies))
            .build()
    }

    override fun provideFeature(launchOptions: ProductLaunchOptions): ProductFeature {
        return object : ProductFeature() {

            override fun getFragment(): FeatureFragment =
                ${featureName.capitalize()}FeatureFragment.newInstance(launchOptions.extras)
        }
    }

    fun releaseFeatureComponent() {
        SuboCommonDi.releaseSuboCommonComponent()
        featureComponent = null
    }
}
""".trimIndent()