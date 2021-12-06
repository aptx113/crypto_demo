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

import com.danteyu.studio.crypto.JSON_FILE
import com.danteyu.studio.crypto.data.source.DataSource
import com.danteyu.studio.crypto.data.source.local.db.CryptoDao
import com.danteyu.studio.crypto.data.source.local.json.JsonParser
import com.danteyu.studio.crypto.model.CurrencyInfo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
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

    override fun getAllCurrencyInfoFlow(): Flow<List<CurrencyInfo>> = dao.getAllCryptoInfo()

    override suspend fun insertCurrencyInfo() = withContext(ioDispatcher) {
        val currencyInfoObjects = jsonParser.getCurrencyInfoFromAsset(JSON_FILE)
        currencyInfoObjects?.let { dao.insertAll(it) }
    }
}