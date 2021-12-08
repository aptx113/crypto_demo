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
package com.danteyu.studio.crypto.data.repository

import com.danteyu.studio.crypto.MainCoroutineRule
import com.danteyu.studio.crypto.NONEXISTENT_FILE
import com.danteyu.studio.crypto.TEST_FILE
import com.danteyu.studio.crypto.data.mockCurrencyInfoList
import com.danteyu.studio.crypto.data.source.DataSource
import com.danteyu.studio.crypto.data.source.local.MockLocalDataSource
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by George Yu in Dec. 2021.
 */
@ExperimentalCoroutinesApi
class DefaultRepositoryTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var repository: Repository
    private lateinit var localDataSource: DataSource

    @Before
    fun setUp() {
        localDataSource = MockLocalDataSource(false)
        repository = DefaultRepository(localDataSource)
    }

    @Test
    fun getAllCurrencyInfo() = runBlockingTest {
        val result = repository.parseJsonAndGetAll(TEST_FILE)?.first()
        Truth.assertThat(result).isEqualTo(mockCurrencyInfoList)
    }

    @Test
    fun getAllCurrencyInfoFail() = runBlockingTest {
        localDataSource = MockLocalDataSource(true)
        repository = DefaultRepository(localDataSource)

        val result = repository.parseJsonAndGetAll(NONEXISTENT_FILE)?.first()
        Truth.assertThat(result).isNull()
    }
}
