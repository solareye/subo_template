package com.github.solareye.subotemplate.module.src.ui.main

fun mainViewModel(
    suboModulePackageName: String,
) = """
package $suboModulePackageName.ui.main

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import $suboModulePackageName.data.repository.FeatureRepository
import $suboModulePackageName.navigation.FeatureRouter
import ru.vtb.smb.core_kit.ResourceProvider
import ru.vtb.smb.subo_common.data.model.onFailure
import ru.vtb.smb.subo_common.data.model.onSuccess
import ru.vtb.smb.subo_common.ui.base.BaseViewModel
import ru.vtb.smb.subo_common.utils.toDefaultError
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: FeatureRepository,
    private val router: FeatureRouter,
    private val resourceProvider: ResourceProvider,
) : BaseViewModel(router) {

    private val _viewState: MutableStateFlow<MainViewState> = MutableStateFlow(MainViewState())
    internal val uiState = _viewState.asStateFlow()

    init {
        loadData()
    }

    override fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getTest()
                .onSuccess {
                    _viewState.value = _viewState.value.copy(
                        isLoading = false,
                        data = it.data
                    )
                }
                .onFailure {
                    router.showStatusScreen(
                        toDefaultError(
                            resourceProvider,
                            ACTION_REPEAT
                        )
                    )
                }
        }
    }

}

""".trimIndent()