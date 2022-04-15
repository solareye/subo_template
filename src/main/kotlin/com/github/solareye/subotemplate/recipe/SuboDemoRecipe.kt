package com.github.solareye.subotemplate.app

import com.android.SdkConstants.FN_BUILD_GRADLE
import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.impl.activities.common.generateManifest
import java.io.File

fun RecipeExecutor.suboDemoTemplate(
    moduleData: ModuleTemplateData,
    defaultPackageName: String,
    featureName: String,
    entityName: String,
    layoutName: String
) {

    val srcDir = moduleData.srcDir
    val resDir = moduleData.resDir
    val rootDir = moduleData.rootDir

    val packageName = moduleData.packageName

    /**
     * build.gradle
     */
    rootDir.resolve(FN_BUILD_GRADLE).delete()
    save(
        gradleBuild(
            packageName,
            featureName
        ),
        rootDir.resolve(FN_BUILD_GRADLE)
    )

    /**
     * Activity etc
     */

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
}