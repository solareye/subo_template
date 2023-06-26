package com.github.solareye.subotemplate.recipe

import com.android.SdkConstants.*
import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.github.solareye.subotemplate.app.*
import java.io.File

fun RecipeExecutor.suboDemoTemplate(
    moduleData: ModuleTemplateData,
    defaultPackageName: String,
    featureName: String,
    featureModuleName: String,
    suboModulePackageName: String,
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
        buildGradleApp(
            packageName,
            featureModuleName
        ),
        rootDir.resolve(FN_BUILD_GRADLE)
    )
    //FIXME: наполнение стандартных файлов без файлов-дублей

    try {
        save(
            buildGradle(),
            File("./").resolve("_$FN_BUILD_GRADLE")
        )
    } catch (ex: Exception) {
        ex.printStackTrace()
    }

    /**
     * gradle.properties
     */
    //FIXME: не работает
    rootDir.resolve(FN_GRADLE_PROPERTIES).delete()
    save(
        gradleProperties(),
        rootDir.resolve(FN_GRADLE_PROPERTIES)
    )
    //FIXME: наполнение стандартных файлов без файлов-дублей
    try {
        save(
            gradleProperties(),
            File("./").resolve("_$FN_GRADLE_PROPERTIES")
        )
    } catch (ex: Exception) {
        ex.printStackTrace()
    }

    /**
     * gradle-wrapper.properties
     */
    //FIXME: не работает
    rootDir.resolve(FN_GRADLE_WRAPPER_PROPERTIES).delete()
    save(
        gradleWrapperProperties(),
        rootDir.resolve(FN_GRADLE_WRAPPER_PROPERTIES)
    )
    //FIXME: наполнение стандартных файлов без файлов-дублей
    //TODO: Не отрабатывает — нельзя добавить файл в папку gradle/wrapper/ ?
    try {
        save(
            gradleWrapperProperties(),
            File("./gradle/wrapper/").resolve("_$FN_GRADLE_WRAPPER_PROPERTIES")
        )
    } catch (ex: Exception) {
        ex.printStackTrace()
    }

    //FIXME: наполнение стандартных файлов без файлов-дублей
    try {
        save(
            settingsGradle(
                featureName,
                featureModuleName,
            ),
            File("./").resolve("_$FN_SETTINGS_GRADLE")
        )
    } catch (ex: Exception) {
        ex.printStackTrace()
    }

    //FIXME: наполнение стандартных файлов без файлов-дублей

    // This will generate new manifest (with activity) to merge it with existing
//    generateManifest(
//        moduleData = moduleData,
//        activityClass = activityClass,
//        packageName = packageName,
//        isLauncher = true,
//        hasNoActionBar = false,
//        activityThemeName = "",
//        generateActivityTitle = false,
//        isResizeable = true,
//    )
    save(
        manifest(
            packageName,
        ),
        rootDir.resolve("src/main/_AndroidManifest.xml")
    )

    /**
     * Activity etc
     */

    val activityClass = "${entityName}Activity"

    save(
        mainActivity(
            packageName,
            suboModulePackageName,
            featureName,
            entityName,
        ),
        srcDir.resolve("$activityClass.kt")
    )
    save(
        mainActivityLayout(entityName),
        resDir.resolve("layout/$layoutName.xml")
    )

    resDir.resolve("values/colors.xml").delete()
    try {
        resDir.resolve("values-night").deleteRecursively()
    } catch (ex: Exception) {
        ex.printStackTrace()
    }
    resDir.resolve("values/themes.xml").delete()
    /**
     * dependencies.gradle
     */
    save(
        dependenciesGradle(),
        File("./").resolve("dependencies.gradle")
    )
    /**
     * deployment.gradle
     */
    save(
        deploymentGradle(),
        File("./").resolve("deployment.gradle")
    )
    /**
     * publishing.gradle
     */
    save(
        publishingGradle(
            featureName,
            defaultPackageName,
        ),
        File("./").resolve("publishing.gradle")
    )

    //Удаление папки test
    try {
        rootDir.resolve("src/test").deleteRecursively()
    } catch (ex: Exception) {
        ex.printStackTrace()
    }

}