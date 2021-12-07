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
package com.danteyu.studio.crypto.data.source.local.db

import androidx.test.filters.SmallTest
import com.danteyu.studio.crypto.model.CurrencyInfo
import com.google.common.truth.Truth
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by George Yu in Dec. 2021.
 */
@SmallTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
class CryptoDaoTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("testDatabase")
    lateinit var db: CryptoDatabase
    private lateinit var dao: CryptoDao

    private val currencyInfoA = CurrencyInfo("ETH", "Ethereum", "ETH")
    private val currencyInfoB = CurrencyInfo("LTC", "Litecoin", "LTC")
    private val currencyInfoC = CurrencyInfo("XLM", "Stellar", "XLM")

    @Before
    fun setup() {
        hiltRule.inject()
        dao = db.cryptoDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun getCurrencyInfo() = runBlockingTest {
        dao.insertAll(listOf(currencyInfoA, currencyInfoB, currencyInfoC))
        val currencyInfoList = dao.getAllCryptoInfo().toList()

        Truth.assertThat(currencyInfoList.size).isEqualTo(3)

        Truth.assertThat(currencyInfoList[1]).isEqualTo(currencyInfoB)
        Truth.assertThat(currencyInfoList[2]).isEqualTo(currencyInfoC)
    }
}
