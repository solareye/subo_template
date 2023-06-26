package com.github.solareye.subotemplate.module.src.integration

import android.databinding.tool.ext.toCamelCase

fun suboFeatureFragment(
    packageName: String,
    featureName: String,
    moduleFragmentContainerLayoutName: String,
) = """
package $packageName.integration

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.github.terrakok.cicerone.Forward
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import $packageName.R
import $packageName.databinding.${moduleFragmentContainerLayoutName.toCamelCase()}Binding
import $packageName.navigation.FeatureRouter
import ru.vtb.smb.core_kit.components.status_fragment.StatusFragmentResult
import ru.vtb.smb.integration_library.FeatureFragment
import ru.vtb.smb.subo_common.di.factory.InjectingSavedStateViewModelFactory
import ru.vtb.smb.subo_common.navigation.DialogScreen
import ru.vtb.smb.subo_common.navigation.NavigationHelper
import ru.vtb.smb.subo_common.ui.base.BaseFragment
import ru.vtb.smb.subo_common.utils.addChildResultListener
import ru.vtb.smb.subo_common.utils.viewBinding
import ru.vtb.smb.ui_kit.showPopupDemoDialog
import javax.inject.Inject

class ${featureName}FeatureFragment : FeatureFragment(), NavigationHelper {

    companion object {
        const val ACTION_SHOW_FEATURE_MAIN_SCREEN = "ACTION_SHOW_FEATURE_MAIN_SCREEN"

        fun newInstance(bundle: Bundle? = null) = ${featureName}FeatureFragment().apply {
            arguments = bundle
        }
    }

    protected val currentFragment
        get() = childFragmentManager.findFragmentById(R.id.fragment_container_id) as? BaseFragment

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var router: FeatureRouter

    @Inject
    lateinit var defaultViewModelFactory: dagger.Lazy<InjectingSavedStateViewModelFactory>

    override val defaultViewModelProviderFactory: ViewModelProvider.Factory
        get() = defaultViewModelFactory.get().create(this, arguments)

    private val viewModel: ${featureName}ViewModel by viewModels()
    private val binding by viewBinding(${moduleFragmentContainerLayoutName.toCamelCase()}Binding::bind)

    override fun onAttach(context: Context) {
        ${featureName}Feature.getFeatureComponent()?.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        router.init(this)

        if (savedInstanceState == null) {
            viewModel.showLaunchScreen()
        }
        addChildResultListener(StatusFragmentResult.RESULT_CURRENT_KEY) { key, bundle ->
            viewModel.onResult(key, bundle)
        }
        addChildResultListener(StatusFragmentResult.RESULT_FLOW_KEY) { _, bundle ->
            when (StatusFragmentResult.fromBundle(bundle)?.action) {
                ACTION_SHOW_FEATURE_MAIN_SCREEN -> router.showMain()
                else                            -> router.exitFromFeatureToMain()
            }
        }
    }

    override fun onDestroy() {
        ${featureName}Feature.releaseFeatureComponent()
        super.onDestroy()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.${moduleFragmentContainerLayoutName}, container, false)
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onBackPressed() {
        currentFragment?.onBackPressed()
    }

    private val navigator: Navigator by lazy {
        object : AppNavigator(
            requireActivity(),
            R.id.fragment_container_id,
            childFragmentManager,
            childFragmentManager.fragmentFactory
        ) {
            override fun activityBack() {
                parentFragmentManager.popBackStack()
            }

            override fun forward(command: Forward) {
                when (val screen = command.screen) {
                    is DialogScreen -> {
                        screen.createFragment().show(fragmentManager, screen.screenKey)
                    }
                    else            -> super.forward(command)
                }
            }
        }
    }

    override fun showDemoDialog() {
        requireContext().showPopupDemoDialog()
    }

    override fun exitFromFeature() {
        parentFragmentManager.popBackStack()
    }
}
""".trimIndent()