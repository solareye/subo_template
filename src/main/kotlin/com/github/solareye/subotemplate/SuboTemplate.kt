package com.github.solareye.subotemplate

import com.android.tools.idea.wizard.template.*
import com.github.solareye.subotemplate.recipe.suboDemoTemplate
import com.github.solareye.subotemplate.recipe.suboModuleTemplate
import java.io.File

private const val MIN_SDK = 26

val suboTemplate
    get() = template {
        name = "Subo Module"
        description = "Создаём новый проект для модуля СУБО"
        minApi = MIN_SDK
        category = Category.Other // Check other categories
        formFactor = FormFactor.Mobile
        screens = listOf(
            WizardUiContext.FragmentGallery, WizardUiContext.MenuEntry,
            WizardUiContext.NewProject, WizardUiContext.NewModule
        )

        val packageNameParam = defaultPackageNameParameter
        val entityName = stringParameter {
            name = "Entity Name"
            default = "Main"
            help = "The name of the entity class to create and use in Activity"
            constraints = listOf(Constraint.NONEMPTY)
        }

        val layoutName = stringParameter {
            name = "Layout Name"
            default = "main"
            help = "The name of the layout to create for the activity"
            constraints = listOf(Constraint.LAYOUT, Constraint.UNIQUE, Constraint.NONEMPTY)
            suggest = { activityToLayout(entityName.value.lowercase()) }
        }

        widgets(
            TextFieldWidget(entityName),
            TextFieldWidget(layoutName),
            PackageNameWidget(packageNameParam)
        )

        thumb { File("logo.png") }

        recipe = { data: TemplateData ->

            data as ModuleTemplateData

            val featureName = data.themesData.appName
            val featureModuleName = "feature_${featureName.lowercase()}"
            val suboModulePackageName = "${packageNameParam.defaultValue}.$featureModuleName"

            suboDemoTemplate(
                data,
                packageNameParam.defaultValue,
                featureName,
                featureModuleName,
                suboModulePackageName,
                entityName.value,
                layoutName.value
            )
            suboModuleTemplate(
                data,
                suboModulePackageName,
                featureName,
                featureModuleName,
            )
        }
    }

val defaultPackageNameParameter
    get() = stringParameter {
        name = "Package name"
        visible = { !isNewModule }
        default = "ru.vtb.smb"
        constraints = listOf(Constraint.PACKAGE)
        suggest = { packageName }
    }