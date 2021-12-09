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

    private val eventDisplayChannel = Channel<Boolean?>(Channel.RENDEZVOUS)
    val eventDisplayFlow = eventDisplayChannel.receiveAsFlow()

    private val eventSortChannel = Channel<Boolean?>(Channel.RENDEZVOUS)
    val eventSortFlow = eventSortChannel.receiveAsFlow()

    private val itemClickChannel = Channel<String>()
    val itemClickFlow = itemClickChannel.receiveAsFlow()

    val displayCurrencyInfoList: () -> Unit = {
        getAllCurrencyInfoFlow(JSON_FILE)
    }

    val sortCurrencyInfoList: () -> Unit = {
        getAllCurrencyInfoFlow(JSON_FILE, true)
    }

    fun getAllCurrencyInfoFlow(fileName: String, shouldSort: Boolean = false) =
        viewModelScope.launch {
            repository.parseJsonAndGetAll(fileName, shouldSort)
                ?.filterNotNull()
                ?.collect {
                    _currencyInfoListFlow.value = it
                }
        }

    fun onDisplayBtnClicked(isClicked: Boolean?) =
        viewModelScope.launch { eventDisplayChannel.send(isClicked) }

    fun onSortBtnClicked(isClicked: Boolean?) =
        viewModelScope.launch { eventSortChannel.send(isClicked) }

    fun onItemClicked(currencyName: String) =
        viewModelScope.launch { itemClickChannel.send(currencyName) }
}
