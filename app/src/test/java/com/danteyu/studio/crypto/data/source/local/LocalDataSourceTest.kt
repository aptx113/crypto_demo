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
package com.danteyu.studio.crypto.data.source.local

import com.danteyu.studio.crypto.MainCoroutineRule
import com.danteyu.studio.crypto.TEST_FILE
import com.danteyu.studio.crypto.data.mockCurrencyInfoList
import com.danteyu.studio.crypto.data.source.local.db.CryptoDao
import com.danteyu.studio.crypto.data.source.local.json.JsonParser
import com.google.common.truth.Truth
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by George Yu in Dec. 2021.
 */
@ExperimentalCoroutinesApi
class LocalDataSourceTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var dao: CryptoDao

    private lateinit var jsonParser: JsonParser

    private lateinit var dispatchers: CoroutineDispatcher

    private lateinit var dataSource: LocalDataSource

    @Before
    fun setUp() {
        dao = mockk {
            coEvery { insertAll(mockCurrencyInfoList) } just Runs
            every { getAllCryptoInfo() } returns flowOf(mockCurrencyInfoList)
        }
        jsonParser =
            mockk { coEvery { getCurrencyInfoFromAsset(TEST_FILE) } returns mockCurrencyInfoList }
        dispatchers = mainCoroutineRule.dispatcher
        dataSource = LocalDataSource(dao, jsonParser, dispatchers)
    }

    @Test
    fun parseJson_insert_getAll() = runBlockingTest {
        val list = dataSource.parseJsonAndGetAll(TEST_FILE, false).first()

        Truth.assertThat(list.size).isEqualTo(4)
        Truth.assertThat(list).isEqualTo(mockCurrencyInfoList)
    }
}
