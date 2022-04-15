package com.github.solareye.subotemplate.module.di.modules

fun viewModelModule(
    packageName: String,
    featureName: String,
) = """
package $packageName.di.modules

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import dagger.multibindings.Multibinds
import $packageName.integration.${featureName.capitalize()}ViewModel
import ru.vtb.smb.subo_common.di.factory.AssistedSavedStateViewModelFactory
import ru.vtb.smb.subo_common.di.modules.ViewModelKey
import kotlin.reflect.KClass

@Module
abstract class ViewModelModule {

    @Multibinds
    abstract fun viewModels(): Map<Class<out ViewModel>, @JvmSuppressWildcards ViewModel>

    @Multibinds
    abstract fun assistedViewModels(): Map<Class<out ViewModel>, @JvmSuppressWildcards AssistedSavedStateViewModelFactory<out ViewModel>>

    @Binds
    @IntoMap
    @ViewModelKey(${featureName.capitalize()}ViewModel::class)
    abstract fun bind${featureName.capitalize()}ViewModel(vm: ${featureName.capitalize()}ViewModel): ViewModel

}
""".trimIndent()