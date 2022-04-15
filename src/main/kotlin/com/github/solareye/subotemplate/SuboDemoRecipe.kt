package com.github.solareye.subotemplate

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.impl.activities.common.addAllKotlinDependencies
import com.android.tools.idea.wizard.template.impl.activities.common.addMaterialDependency
import com.android.tools.idea.wizard.template.impl.activities.common.generateManifest
import com.intellij.openapi.module.ModuleManager
import com.intellij.openapi.roots.ModuleRootManager
import org.jetbrains.kotlin.idea.caches.project.NotUnderContentRootModuleInfo.project
import java.io.File

fun RecipeExecutor.suboDemoTemplate(
    moduleData: ModuleTemplateData,
    defaultPackageName: String,
    featureName: String,
    entityName: String,
    layoutName: String
) {
    val (
        projectTemplateData,
        srcDir,
        resDir,
        rootDir,
    ) = moduleData

    val packageName = moduleData.packageName

    val activityClass = "${entityName}Activity"
    // This will generate new manifest (with activity) to merge it with existing
    generateManifest(
        moduleData = moduleData,
        activityClass = activityClass,
        packageName = packageName,
        isLauncher = true,
        hasNoActionBar = false,
        generateActivityTitle = true
    )

    save(
        someActivity(
            packageName,
            entityName,
        ),
        srcDir.resolve("$activityClass.kt")
    )
    save(
        someActivityLayout(entityName),
        resDir.resolve("layout/$layoutName.xml")
    )
    //dependencies.gradle
    save(
        dependenciesValues(),
        File("./").resolve("dependencies.gradle")
    )

    //build.gradle
    try {
        applyPlugin("kotlin-kapt", "")
    } catch (ex: Exception) {
        ex.printStackTrace()
    }

    try {
        addModuleDependency(
            configuration = "implementation",
            moduleName = featureName,
            File("./").resolve(featureName)
        )
    } catch (ex: Exception) {
        ex.printStackTrace()
    }

    try {
        setBuildFeature("buildConfig", true)
        setBuildFeature("viewBinding", true)
    } catch (ex: Exception) {
        ex.printStackTrace()
    }

    try {
        addMaterialDependency(true)
        addDependency("androidUI.constraint", "implementation")
        addDependency("vtbDeps.integration", "implementation")
        addDependency("vtbDeps.core", "implementation")
        addDependency("vtbDeps.subo_demo", "implementation")
        addDependency("androidx.core:core-ktx:7.8.0", "implementation")
    } catch (ex: Exception) {
        ex.printStackTrace()
    }
}