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

import com.danteyu.studio.crypto.data.source.DataSource
import com.danteyu.studio.crypto.data.source.local.db.CryptoDao
import com.danteyu.studio.crypto.data.source.local.json.JsonParser
import com.danteyu.studio.crypto.model.CurrencyInfo
import com.danteyu.studio.crypto.ui.common.ControlledRunner
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by George Yu in Dec. 2021.
 */
class LocalDataSource @Inject constructor(
    private val dao: CryptoDao,
    private val jsonParser: JsonParser,
    private val ioDispatcher: CoroutineDispatcher
) :
    DataSource {

    var controlledRunner = ControlledRunner<Flow<List<CurrencyInfo>>>()
    override suspend fun parseJsonAndGetAll(
        fileName: String,
        shouldSort: Boolean
    ): Flow<List<CurrencyInfo>> =
        withContext(ioDispatcher) {
            val currencyInfoObjects = jsonParser.getCurrencyInfoFromAsset(fileName)
            currencyInfoObjects?.let { dao.insertAll(it) }

            controlledRunner.cancelPreviousThenRun {
                Timber.d("ORZ: $shouldSort")
                if (shouldSort) dao.getAllCryptoInfoAscending()
                else dao.getAllCryptoInfo()
            }
        }
}
