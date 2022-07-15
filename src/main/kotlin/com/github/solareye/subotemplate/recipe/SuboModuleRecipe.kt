package com.github.solareye.subotemplate.recipe

import com.android.SdkConstants
import com.android.tools.idea.npw.module.recipes.createDefaultDirectories
import com.android.tools.idea.npw.module.recipes.gitignore
import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.github.solareye.subotemplate.module.src.data.errorMapper
import com.github.solareye.subotemplate.module.src.data.mapper
import com.github.solareye.subotemplate.module.src.data.model.testDto
import com.github.solareye.subotemplate.module.src.data.remote.endpointRestApi
import com.github.solareye.subotemplate.module.src.data.remote.mockRestApi
import com.github.solareye.subotemplate.module.src.data.repository.featureRepository
import com.github.solareye.subotemplate.module.src.data.repository.featureRepositoryImpl
import com.github.solareye.subotemplate.module.src.di.components.appComponent
import com.github.solareye.subotemplate.module.src.di.components.featureComponent
import com.github.solareye.subotemplate.module.src.di.modules.appModule
import com.github.solareye.subotemplate.module.src.di.modules.featureModule
import com.github.solareye.subotemplate.module.src.di.modules.networkModule
import com.github.solareye.subotemplate.module.src.di.modules.viewModelModule
import com.github.solareye.subotemplate.module.src.integration.suboFeature
import com.github.solareye.subotemplate.module.src.integration.suboFeatureFragment
import com.github.solareye.subotemplate.module.src.integration.suboViewModel
import com.github.solareye.subotemplate.module.src.model.test
import com.github.solareye.subotemplate.module.src.navigation.featureRouter
import com.github.solareye.subotemplate.module.src.navigation.screens
import com.github.solareye.subotemplate.module.configPropertiesModule
import com.github.solareye.subotemplate.module.buildGradleModule
import com.github.solareye.subotemplate.module.res.layout.mainFragmentLayout
import com.github.solareye.subotemplate.module.res.layout.moduleFragmentContainerLayout
import com.github.solareye.subotemplate.module.manifestModule
import com.github.solareye.subotemplate.module.src.ui.main.mainFragment
import com.github.solareye.subotemplate.module.src.ui.main.mainViewModel
import com.github.solareye.subotemplate.module.src.ui.main.mainViewState
import com.github.solareye.subotemplate.module.res.values.strings
import java.io.File

fun RecipeExecutor.suboModuleTemplate(
    data: ModuleTemplateData,
    suboModulePackageName: String,
    featureName: String,
    featureModuleName: String,
) {

    val moduleOut = File("$featureModuleName/")
    val srcOut = File("$moduleOut/src/main/java/${suboModulePackageName.replace(".", "/")}")
    val resOut = File("$moduleOut/src/main/res/")
    val manifestOut = File("$moduleOut/src/main/")

    createDefaultDirectories(moduleOut, srcOut)

    try {
        save(configPropertiesModule(), moduleOut.resolve("config.properties"))
    } catch (ex: Exception) {
        ex.printStackTrace()
    }
    try {
        save(buildGradleModule(), moduleOut.resolve("build.gradle"))
    } catch (ex: Exception) {
        ex.printStackTrace()
    }
    try {
        save(
            manifestModule(suboModulePackageName),
            manifestOut.resolve(SdkConstants.FN_ANDROID_MANIFEST_XML)
        )
    } catch (ex: Exception) {
        ex.printStackTrace()
    }
    try {
        save(gitignore(), moduleOut.resolve(".gitignore"))
    } catch (ex: Exception) {
        ex.printStackTrace()
    }

    val featureNamePrefix = featureName.substring(0, 3).lowercase()

    /**
     * strings
     */
    try {
        save(
            strings(
                featureNamePrefix
            ),
            resOut.resolve("values/strings.xml")
        )
    } catch (ex: Exception) {
        ex.printStackTrace()
    }

    /**
     * data
     */
    try {
        save(
            errorMapper(
                suboModulePackageName,
            ),
            srcOut.resolve("data/ErrorMapper.kt")
        )
    } catch (ex: Exception) {
        ex.printStackTrace()
    }

    try {
        save(
            mapper(
                suboModulePackageName,
            ),
            srcOut.resolve("data/Mapper.kt")
        )
    } catch (ex: Exception) {
        ex.printStackTrace()
    }

    /**
     * data.model
     */

    try {
        save(
            testDto(
                suboModulePackageName,
            ),
            srcOut.resolve("data/model/TestDto.kt")
        )
    } catch (ex: Exception) {
        ex.printStackTrace()
    }

    /**
     * data.remote
     */

    try {
        save(
            endpointRestApi(
                suboModulePackageName,
            ),
            srcOut.resolve("data/remote/EndpointRestApi.kt")
        )
    } catch (ex: Exception) {
        ex.printStackTrace()
    }

    try {
        save(
            mockRestApi(
                suboModulePackageName,
            ),
            srcOut.resolve("data/remote/MockRestApi.kt")
        )
    } catch (ex: Exception) {
        ex.printStackTrace()
    }

    /**
     * data.repository
     */

    try {
        save(
            featureRepository(
                suboModulePackageName,
            ),
            srcOut.resolve("data/repository/FeatureRepository.kt")
        )
    } catch (ex: Exception) {
        ex.printStackTrace()
    }

    try {
        save(
            featureRepositoryImpl(
                suboModulePackageName,
            ),
            srcOut.resolve("data/repository/FeatureRepositoryImpl.kt")
        )
    } catch (ex: Exception) {
        ex.printStackTrace()
    }

    /**
     * di
     */

    /**
     * di.components
     */

    try {
        save(
            appComponent(
                suboModulePackageName,
            ),
            srcOut.resolve("di/components/AppComponent.kt")
        )
    } catch (ex: Exception) {
        ex.printStackTrace()
    }

    try {
        save(
            featureComponent(
                suboModulePackageName,
                featureName,
            ),
            srcOut.resolve("di/components/FeatureComponent.kt")
        )
    } catch (ex: Exception) {
        ex.printStackTrace()
    }

    /**
     * di.modules
     */

    try {
        save(
            appModule(
                suboModulePackageName,
            ),
            srcOut.resolve("di/modules/AppModule.kt")
        )
    } catch (ex: Exception) {
        ex.printStackTrace()
    }

    try {
        save(
            featureModule(
                suboModulePackageName,
            ),
            srcOut.resolve("di/modules/FeatureModule.kt")
        )
    } catch (ex: Exception) {
        ex.printStackTrace()
    }

    try {
        save(
            networkModule(
                suboModulePackageName,
                featureName,
            ),
            srcOut.resolve("di/modules/NetworkModule.kt")
        )
    } catch (ex: Exception) {
        ex.printStackTrace()
    }

    try {
        save(
            viewModelModule(
                suboModulePackageName,
                featureName,
            ),
            srcOut.resolve("di/modules/ViewModelModule.kt")
        )
    } catch (ex: Exception) {
        ex.printStackTrace()
    }

    /**
     * integration
     */

    try {
        save(
            suboFeature(
                suboModulePackageName,
                featureName,
            ),
            srcOut.resolve("integration/${featureName}Feature.kt")
        )
    } catch (ex: Exception) {
        ex.printStackTrace()
    }

    val moduleFragmentContainerLayoutName = "${featureNamePrefix}_fragment_container"

    try {
        save(
            suboFeatureFragment(
                suboModulePackageName,
                featureName,
                moduleFragmentContainerLayoutName,
            ),
            srcOut.resolve("integration/${featureName}FeatureFragment.kt")
        )
    } catch (ex: Exception) {
        ex.printStackTrace()
    }

    try {
        save(
            moduleFragmentContainerLayout(),
            resOut.resolve("layout/${moduleFragmentContainerLayoutName}.xml")
        )
    } catch (ex: Exception) {
        ex.printStackTrace()
    }

    try {
        save(
            suboViewModel(
                suboModulePackageName,
                featureName,
            ),
            srcOut.resolve("integration/${featureName}ViewModel.kt")
        )
    } catch (ex: Exception) {
        ex.printStackTrace()
    }

    /**
     * model
     */

    try {
        save(
            test(
                suboModulePackageName,
            ),
            srcOut.resolve("model/Test.kt")
        )
    } catch (ex: Exception) {
        ex.printStackTrace()
    }

    /**
     * navigation
     */

    try {
        save(
            featureRouter(
                suboModulePackageName,
            ),
            srcOut.resolve("navigation/FeatureRouter.kt")
        )
    } catch (ex: Exception) {
        ex.printStackTrace()
    }

    try {
        save(
            screens(
                suboModulePackageName,
            ),
            srcOut.resolve("navigation/Screens.kt")
        )
    } catch (ex: Exception) {
        ex.printStackTrace()
    }

    /**
     * ui
     */
    val mainLayoutName = "${featureNamePrefix}_fragment_main"

    try {
        save(
            mainFragment(
                featureName,
                suboModulePackageName,
                mainLayoutName,
            ),
            srcOut.resolve("ui/main/MainFragment.kt")
        )
    } catch (ex: Exception) {
        ex.printStackTrace()
    }

    try {
        save(
            mainFragmentLayout(
                featureNamePrefix,
            ),
            resOut.resolve("layout/${featureNamePrefix}_fragment_main.xml")
        )
    } catch (ex: Exception) {
        ex.printStackTrace()
    }

    try {
        save(
            mainViewState(
                suboModulePackageName,
            ),
            srcOut.resolve("ui/main/MainViewState.kt")
        )
    } catch (ex: Exception) {
        ex.printStackTrace()
    }

    try {
        save(
            mainViewModel(
                suboModulePackageName,
            ),
            srcOut.resolve("ui/main/MainViewModel.kt")
        )
    } catch (ex: Exception) {
        ex.printStackTrace()
    }

}