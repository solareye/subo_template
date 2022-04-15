package com.github.solareye.subotemplate

import com.android.tools.idea.gradle.dsl.api.GradleSettingsModel
import com.android.tools.idea.wizard.template.*
import java.io.File
import java.net.URL

private const val MIN_SDK = 23

val suboTemplate
    get() = template {
        thumb { File("template_base_fragment.png") }
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
            suggest = { activityToLayout(entityName.value.toLowerCase()) }
        }

        widgets(
            TextFieldWidget(entityName),
            TextFieldWidget(layoutName),
            PackageNameWidget(packageNameParam)
        )

        recipe = { data: TemplateData ->

            data as ModuleTemplateData

            val featureSuffix = data.packageName.split(".").last()
            val featureName = "feature_${featureSuffix}"

            suboDemoTemplate(
                data,
                packageNameParam.defaultValue,
                featureName,
                entityName.value,
                layoutName.value
            )
            suboModuleTemplate(
                data,
                packageNameParam.defaultValue,
                featureName,
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