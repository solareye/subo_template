package com.github.solareye.subotemplate.recipe

import com.android.SdkConstants
import com.android.tools.idea.npw.module.recipes.createDefaultDirectories
import com.android.tools.idea.npw.module.recipes.gitignore
import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.github.solareye.subotemplate.suboConfigProperties
import com.github.solareye.subotemplate.suboGradleFile
import com.github.solareye.subotemplate.suboModuleManifest
import java.io.File

fun RecipeExecutor.suboModuleTemplate(
    data: ModuleTemplateData,
    packageNamedefaultValue: String,
    featureName: String
) {

    val suboModulePackageName = "${packageNamedefaultValue}.$featureName"

    val moduleOut = File("$featureName/")
    val srcOut = File("$moduleOut/src/main/java/${suboModulePackageName.replace(".", "/")}")
    val resOut = File("$moduleOut/src/main/res/")
    val manifestOut = File("$moduleOut/src/main/")

    createDefaultDirectories(moduleOut, srcOut)

    try {
        save(suboConfigProperties(), moduleOut.resolve("config.properties"))
    } catch (ex: Exception) {
        ex.printStackTrace()
    }
    try {
        save(suboGradleFile(), moduleOut.resolve("build.gradle"))
    } catch (ex: Exception) {
        ex.printStackTrace()
    }
    try {
        save(
            suboModuleManifest(suboModulePackageName),
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
    
    try {
        addIncludeToSettings(featureName)
    } catch (ex: Exception) {
        ex.printStackTrace()
    }

}