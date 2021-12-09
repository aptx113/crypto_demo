/*
 * Copyright 2021 Dante Yu
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.danteyu.studio.crypto

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danteyu.studio.crypto.data.repository.Repository
import com.danteyu.studio.crypto.model.CurrencyInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by George Yu in Dec. 2021.
 */
@HiltViewModel
class DemoViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _currencyInfoListFlow = MutableStateFlow<List<CurrencyInfo>>(listOf())
    val currencyInfoListFlow: StateFlow<List<CurrencyInfo>> = _currencyInfoListFlow

    val eventDisplayChannel = Channel<Unit>()
    val eventDisplayFlow = eventDisplayChannel.receiveAsFlow()

    val eventSortChannel = Channel<Unit>()
    val eventSortFlow = eventSortChannel.receiveAsFlow()

    val eventClickChannel = Channel<String>(Channel.CONFLATED)

    fun getAllCurrencyInfoFlow(fileName: String, shouldSort: Boolean = false) =
        viewModelScope.launch {
            repository.parseJsonAndGetAll(fileName)
                ?.filterNotNull()
                ?.map { currencyInfoList ->
                    if (shouldSort) currencyInfoList.sortedBy { it.symbol }
                    else currencyInfoList
                }
                ?.collect {
                    _currencyInfoListFlow.value = it
                }
        }
}
