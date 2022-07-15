package com.github.solareye.subotemplate.module.src.di.modules

fun viewModelModule(
    packageName: String,
    featureName: String,
) = """
package $packageName.di.modules

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dagger.multibindings.Multibinds
import $packageName.integration.${featureName}ViewModel
import $packageName.ui.main.MainViewModel
import ru.vtb.smb.subo_common.di.factory.AssistedSavedStateViewModelFactory
import ru.vtb.smb.subo_common.di.modules.ViewModelKey

@Module
abstract class ViewModelModule {

    @Multibinds
    abstract fun viewModels(): Map<Class<out ViewModel>, @JvmSuppressWildcards ViewModel>

    @Multibinds
    abstract fun assistedViewModels(): Map<Class<out ViewModel>, @JvmSuppressWildcards AssistedSavedStateViewModelFactory<out ViewModel>>

    @Binds
    @IntoMap
    @ViewModelKey(${featureName}ViewModel::class)
    abstract fun bind${featureName}ViewModel(vm: ${featureName}ViewModel): ViewModel
    
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(vm: MainViewModel): ViewModel

}
""".trimIndent()