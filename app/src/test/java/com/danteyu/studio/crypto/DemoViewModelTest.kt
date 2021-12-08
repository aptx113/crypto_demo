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
        demoViewModel.getAllCurrencyInfoFlow(TEST_FILE)
        val currencyInListFlow = demoViewModel.currencyInfoListFlow

        Truth.assertThat(currencyInListFlow.first().size).isEqualTo(
            mockCurrencyInfoList.size
        )
        Truth.assertThat(currencyInListFlow.first()[0]).isEqualTo(mockCurrencyInfoList[0])
    }

    @Test
    fun sendDisplayEvent() = runBlockingTest {
        demoViewModel.onDisplayBtnClicked()

        Truth.assertThat(demoViewModel.eventDisplayFlow.first()).isEqualTo(true)
    }

    @Test
    fun sendSortEvent() = runBlockingTest {
        demoViewModel.onSortBtnClicked()

        Truth.assertThat(demoViewModel.eventSortFlow.first()).isEqualTo(true)
    }
}
