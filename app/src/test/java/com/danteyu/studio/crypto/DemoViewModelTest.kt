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

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.danteyu.studio.crypto.data.mockCurrencyInfoList
import com.danteyu.studio.crypto.data.repository.MockDefaultRepository
import com.danteyu.studio.crypto.model.CurrencyInfo
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

/**
 * Created by George Yu in 12月. 2021.
 */
@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
class DemoViewModelTest {

    private lateinit var demoViewModel: DemoViewModel

    private lateinit var repository: MockDefaultRepository

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var currencyInfoList = listOf<CurrencyInfo>()

    @Before
    fun setUp() {
        repository = MockDefaultRepository()

        currencyInfoList = mockCurrencyInfoList
        repository.currencyInfoListFlow = flow { emit(currencyInfoList) }

        demoViewModel = DemoViewModel(repository)
    }

    @Test
    fun loadCurrencyList_getCurrencyList() = runBlockingTest {
        demoViewModel.parseJsonAndInsert(TEST_FILE)
        demoViewModel.getAllCurrencyInfo()
        val currencyInListFlow = demoViewModel.currencyInfoListFlow

        Truth.assertThat(currencyInListFlow.first().size).isEqualTo(
            mockCurrencyInfoList.size
        )
        Truth.assertThat(currencyInListFlow.first()[0]).isEqualTo(mockCurrencyInfoList[0])
    }

    @Test
    fun sendClickItem() = runBlockingTest {
        demoViewModel.onItemClicked("")

        Truth.assertThat(demoViewModel.itemClickFlow.first()).isEqualTo("")
    }
}
