package com.github.solareye.subotemplate.module.src.ui.main

import android.databinding.tool.ext.toCamelCase

fun mainFragment(
    featureName: String,
    suboModulePackageName: String,
    layoutName: String
) = """
package $suboModulePackageName.ui.main

import androidx.fragment.app.viewModels
import $suboModulePackageName.R
import $suboModulePackageName.databinding.${layoutName.toCamelCase()}Binding
import $suboModulePackageName.integration.${featureName}Feature
import ru.vtb.smb.subo_common.ui.base.BaseFragment
import ru.vtb.smb.subo_common.ui.base.BaseViewModel
import ru.vtb.smb.subo_common.utils.viewBinding

class MainFragment @Deprecated("Используйте newInstance()") constructor() :
    BaseFragment(R.layout.$layoutName) {

    companion object {

        @Suppress("DEPRECATION")
        fun newInstance() = MainFragment()
    }

    private val binding by viewBinding(${layoutName.toCamelCase()}Binding::bind)
    private val viewModel: MainViewModel by viewModels()

    override fun getViewModel(): BaseViewModel = viewModel

    override fun inject() {
        ${featureName}Feature.getFeatureComponent()?.inject(this)
    }

    override fun initView() {
        binding.toolbar.apply {
            setNavigationOnClickListener {
                onBackPressed()
            }
        }
    }

    override fun bind() {

    }

}
""".trimIndent()